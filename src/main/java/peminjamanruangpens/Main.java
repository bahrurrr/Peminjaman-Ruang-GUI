package peminjamanruangpens;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Kelas Main mengatur antarmuka pengguna berbasis JavaFX (GUI).
 * Bertindak sebagai *View* yang merender elemen visual dan menghubungkan 
 * aksi klik tombol (Events) ke logika bisnis di kelas SistemPeminjaman.
 * 
 * @author Luthfi Bahrur Rozaq, Bagus Sri Wisnu, Aslam Aji Prasetyo
 * @version 1.0
 */
public class Main extends Application {

    private static SistemPeminjaman sistemPeminjaman = new SistemPeminjaman();

    /**
     * Mengambil instance dari SistemPeminjaman yang sedang berjalan.
     * 
     * @return Objek SistemPeminjaman aktif.
     */
    public static SistemPeminjaman getSistem() {
        return sistemPeminjaman;
    }

    /**
     * Metode utama (*entry point*) untuk JavaFX yang akan dipanggil pertama kali.
     * Mengatur *Stage* utama (jendela program) dan merender menu *dashboard*.
     * 
     * @param primaryStage Jendela/Window utama aplikasi.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard - Sistem Peminjaman Ruangan PENS");

        Label titleLabel = new Label("SISTEM PEMINJAMAN RUANGAN PENS");
        titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Button btnLihatPinjam = new Button("1. Lihat Jadwal & Pinjam Ruangan");
        Button btnRegMember = new Button("2. Registrasi Member Baru");
        Button btnTambahRuang = new Button("3. Tambah Ruangan Baru");
        Button btnKeluar = new Button("4. Keluar");

        String buttonStyle = "-fx-font-size: 13px; -fx-pref-width: 280px; -fx-pref-height: 38px; -fx-cursor: hand; -fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px;";
        String exitStyle = "-fx-font-size: 13px; -fx-pref-width: 280px; -fx-pref-height: 38px; -fx-cursor: hand; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px;";

        btnLihatPinjam.setStyle(buttonStyle);
        btnRegMember.setStyle(buttonStyle);
        btnTambahRuang.setStyle(buttonStyle);
        btnKeluar.setStyle(exitStyle);

        // Menghubungkan Tombol Menu ke Fungsi Form Masing-masing
        btnLihatPinjam.setOnAction(e -> bukaFormLihatDanPinjam());
        btnRegMember.setOnAction(e -> bukaFormRegistrasiMember());
        btnTambahRuang.setOnAction(e -> bukaFormTambahRuangan());

        btnKeluar.setOnAction(e -> primaryStage.close());

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #ecf0f1;");
        root.getChildren().addAll(titleLabel, btnLihatPinjam, btnRegMember, btnTambahRuang, btnKeluar);

        Scene scene = new Scene(root, 420, 380);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Membuka *window* (Form 1) untuk melihat jadwal ketersediaan ruangan 
     * dan mengeksekusi peminjaman ruangan.
     */
    private void bukaFormLihatDanPinjam() {
        Stage stage = new Stage();
        stage.setTitle("Lihat Jadwal & Peminjaman Ruangan");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);

        Label lblPilihRuang = new Label("Pilih Ruangan:");
        ComboBox<String> comboRuang = new ComboBox<>();
        for (Ruang r : sistemPeminjaman.getDaftarRuang()) {
            comboRuang.getItems().add(r.getNamaRuang());
        }
        if (!comboRuang.getItems().isEmpty()) comboRuang.getSelectionModel().selectFirst();

        Label lblTanggal = new Label("Masukkan Tanggal (Cth: 2026-09-23):");
        TextField txtTanggal = new TextField("2026-09-23");

        Button btnCek = new Button("Cek Jadwal");
        
        TextArea areaJadwal = new TextArea();
        areaJadwal.setEditable(false);
        areaJadwal.setPrefHeight(100);

        Label lblPilihSesi = new Label("Pilih Nomor Sesi (1 - 3):");
        TextField txtSesi = new TextField();

        Label lblNrp = new Label("Masukkan NRP Peminjam (3 digit):");
        TextField txtNrp = new TextField();

        Button btnPinjam = new Button("Konfirmasi Peminjaman");
        btnPinjam.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-weight: bold;");

        btnCek.setOnAction(e -> {
            int idx = comboRuang.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                Ruang selectedRuang = sistemPeminjaman.getDaftarRuang().get(idx);
                String tgl = txtTanggal.getText();
                
                Sesi[] jadwal = selectedRuang.getJadwalHarian(tgl);
                StringBuilder sb = new StringBuilder();
                sb.append("Ruangan: ").append(selectedRuang.getNamaRuang())
                  .append(" (Kapasitas: ").append(selectedRuang.getKapasitas()).append(" orang)\n");
                for (Sesi s : jadwal) {
                    sb.append(s.getStatus()).append("\n");
                }
                areaJadwal.setText(sb.toString());
            }
        });

        btnPinjam.setOnAction(e -> {
            try {
                int idx = comboRuang.getSelectionModel().getSelectedIndex();
                Ruang selectedRuang = sistemPeminjaman.getDaftarRuang().get(idx);
                String tgl = txtTanggal.getText();
                int sesiId = Integer.parseInt(txtSesi.getText());
                String nrp = txtNrp.getText();

                String hasil = sistemPeminjaman.prosesPeminjaman(selectedRuang, tgl, sesiId, nrp);
                lblStatus.setText(hasil);
                btnCek.fire();
            } catch (Exception ex) {
                lblStatus.setText("Error: Periksa kembali input sesi atau data anda!");
            }
        });

        layout.getChildren().addAll(
            lblPilihRuang, comboRuang, 
            lblTanggal, txtTanggal, 
            btnCek, 
            areaJadwal, 
            lblPilihSesi, txtSesi, 
            lblNrp, txtNrp, 
            btnPinjam, 
            lblStatus
        );

        Scene scene = new Scene(layout, 450, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Membuka *window* (Form 2) untuk mendaftarkan data Member (NRP dan Nama) ke dalam sistem.
     */
    private void bukaFormRegistrasiMember() {
        Stage stage = new Stage();
        stage.setTitle("Registrasi Member Baru");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);

        Label lblNama = new Label("Masukkan Nama Lengkap:");
        TextField txtNama = new TextField();

        Label lblNrp = new Label("Masukkan NRP (3 digit belakang):");
        TextField txtNrp = new TextField();

        Button btnDaftar = new Button("Daftar Member");
        btnDaftar.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-weight: bold;");

        btnDaftar.setOnAction(e -> {
            String nama = txtNama.getText();
            String nrp = txtNrp.getText();

            String hasil = sistemPeminjaman.prosesRegistrasiMember(nama, nrp);
            lblStatus.setText(hasil);

            if (hasil.startsWith("Sukses")) {
                txtNama.clear();
                txtNrp.clear();
            }
        });

        layout.getChildren().addAll(lblNama, txtNama, lblNrp, txtNrp, btnDaftar, lblStatus);

        Scene scene = new Scene(layout, 380, 260);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Membuka *window* (Form 3) untuk menambah data Ruangan baru ke dalam sistem 
     * beserta kapasitasnya.
     */
    private void bukaFormTambahRuangan() {
        Stage stage = new Stage();
        stage.setTitle("Tambah Ruangan Baru");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER_LEFT);

        Label lblNamaRuang = new Label("Masukkan Nama Ruangan:");
        TextField txtNamaRuang = new TextField();

        Label lblKapasitas = new Label("Masukkan Kapasitas Ruangan:");
        TextField txtKapasitas = new TextField();

        Button btnTambah = new Button("Tambah Ruangan");
        btnTambah.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblStatus = new Label();
        lblStatus.setStyle("-fx-font-weight: bold;");

        btnTambah.setOnAction(e -> {
            String nama = txtNamaRuang.getText();
            String kapasitas = txtKapasitas.getText();

            String hasil = sistemPeminjaman.prosesTambahRuangan(nama, kapasitas);
            lblStatus.setText(hasil);

            if (hasil.startsWith("Sukses")) {
                txtNamaRuang.clear();
                txtKapasitas.clear();
            }
        });

        layout.getChildren().addAll(lblNamaRuang, txtNamaRuang, lblKapasitas, txtKapasitas, btnTambah, lblStatus);

        Scene scene = new Scene(layout, 380, 260);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fungsi `main` standar untuk meluncurkan aplikasi Java.
     * 
     * @param args Argumen dari *command line*.
     */
    public static void main(String[] args) {
        launch(args);
    }
}