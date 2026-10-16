package peminjamanruangpens;
import java.util.LinkedList; 
import java.util.Scanner;

public class SistemPeminjaman {
    private LinkedList<Ruang> daftarRuang;
    private LinkedList<Member> daftarMember;
    private Scanner input;

    public SistemPeminjaman() {
        this.daftarRuang = new LinkedList<>();
        this.daftarMember = new LinkedList<>();
        this.input = new Scanner(System.in);

        daftarRuang.add(new Ruang("Lab APD", 30));
        daftarRuang.add(new Ruang("Lab Jaringan", 30));
        daftarRuang.add(new Ruang("Ruang B201", 60));
        
        daftarMember.add(new Member("Luthfi Bahrur Rozaq", "069"));
    }

    public void mulai() {
        int pilihan;
        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANGAN PENS ===");
            System.out.println("1. Lihat Jadwal & Pinjam Ruangan");
            System.out.println("2. Registrasi Member Baru");
            System.out.println("3. Tambah Ruangan Baru");
            System.out.println("4. Keluar");
            System.out.print("Masukkan pilihan anda : ");
            
            pilihan = input.nextInt();
            input.nextLine();
            
            switch(pilihan) {
                case 1:
                    menuLihatDanPinjam();
                    break;
                case 2:
                    menuTambahMember();
                    break;
                case 3:
                    menuTambahRuangan();
                    break;
                case 4:
                    System.out.println("Sistem ditutup. Selamat tinggal!");
                    break;
                default:
                    System.out.println("Pilihan tidak ada di menu.");
            }
        } while(pilihan != 4);
    }
    
    private void menuLihatDanPinjam() {
        if (daftarRuang.isEmpty()) {
            System.out.println("Belum ada ruangan yang terdaftar di sistem!");
            return;
        }

        System.out.println("\nRuangan mana yang ingin anda lihat :");
        for (int i = 0; i < daftarRuang.size(); i++) {
            System.out.println((i + 1) + ". " + daftarRuang.get(i).getNamaRuang());
        }
        System.out.print("Masukkan pilihan anda : ");
        int pilruang = input.nextInt();
        input.nextLine(); 
        
        if (pilruang >= 1 && pilruang <= daftarRuang.size()) {
            System.out.print("Masukkan tanggal (Cth: 2026-09-25) : ");
            String tanggal = input.nextLine();
            
            Ruang ruangCek = daftarRuang.get(pilruang - 1);

            System.out.println("\n=== DETAIL & JADWAL RUANGAN ===");
            System.out.println("Nama Ruangan : " + ruangCek.getNamaRuang());
            System.out.println("Kapasitas    : " + ruangCek.getKapasitas() + " orang");
            System.out.println("Jadwal Tgl   : " + tanggal);
            
            Sesi[] jadwalHariItu = ruangCek.getJadwalHarian(tanggal);
            for (int i = 0; i < jadwalHariItu.length; i++) {
                System.out.println(jadwalHariItu[i].getStatus());
            }

            System.out.print("\nApakah anda ingin memesan ruangan ini? (y/n) : ");
            String mauPesan = input.nextLine();

            if (mauPesan.equalsIgnoreCase("y")) {
                System.out.print("Masukkan nomor sesi yang ingin dipinjam (1-3) : ");
                int pilsesi = input.nextInt();
                input.nextLine();

                if (pilsesi >= 1 && pilsesi <= 3) {
                    Sesi sesiygDipilih = ruangCek.getSesiSpesifik(tanggal, pilsesi);

                    if (sesiygDipilih.getTersedia()) {
                        System.out.print("Masukkan NRP Anda (3 digit belakang) : ");
                        String nrpInput = input.nextLine();
                        
                        Member pencarianMember = cariMember(nrpInput);

                        if (pencarianMember != null) {
                            sesiygDipilih.pinjam(pencarianMember); 
                            System.out.println("\nPeminjaman berhasil atas nama: " + pencarianMember.getNama());
                        } else {
                            System.out.println("\nPeminjaman Gagal: NRP tidak terdaftar. Silakan registrasi member terlebih dahulu.");
                        }
                    } else {
                        System.out.println("\nMaaf, sesi tersebut sudah dipinjam.");
                    }
                } else {
                    System.out.println("\nPilihan sesi tidak valid.");
                }
            }
        } else {
            System.out.println("Pilihan ruang tidak valid!");
        }
    }
    
    private void menuTambahMember() {
        System.out.println("\n=== REGISTRASI MEMBER BARU ===");
        System.out.print("Masukkan Nama Lengkap : ");
        String namaBaru = input.nextLine();
        System.out.print("Masukkan NRP (3 digit belakang) : ");
        String nrpBaru = input.nextLine();
        
        if (cariMember(nrpBaru) == null) {
            daftarMember.add(new Member(namaBaru, nrpBaru)); 
            System.out.println("Member atas nama " + namaBaru + " berhasil didaftarkan!");
        } else {
            System.out.println("Gagal: NRP tersebut sudah terdaftar di sistem.");
        }
    }
    
    private void menuTambahRuangan() {
        System.out.println("\n=== TAMBAH RUANGAN BARU ===");
        System.out.print("Masukkan Nama Ruangan : ");
        String namaRuangBaru = input.nextLine();
        System.out.print("Masukkan Kapasitas Ruangan : ");
        int kapasitasBaru = input.nextInt();

        daftarRuang.add(new Ruang(namaRuangBaru, kapasitasBaru)); 
        System.out.println("Ruangan " + namaRuangBaru + " berhasil ditambahkan ke sistem!");
    }

    private Member cariMember(String nrpDicari) {
        for (int i = 0; i < daftarMember.size(); i++) {
            Member memberSaatIni = daftarMember.get(i);
            if (memberSaatIni.getNrp().equals(nrpDicari)) {
                return memberSaatIni; 
            }
        }
        return null; 
    }
    
    // Method untuk mengambil daftar ruang agar bisa dibaca oleh GUI
    public LinkedList<Ruang> getDaftarRuang() {
        return daftarRuang;
    }

    // Method pembungkus untuk memproses peminjaman dari GUI
    public String prosesPeminjaman(Ruang ruang, String tanggal, int pilsesi, String nrp) {
        Member pencarianMember = cariMember(nrp);

        if (pencarianMember == null) {
            return "Gagal: NRP " + nrp + " tidak terdaftar. Registrasi dulu di menu 2!";
        }

        Sesi sesiPilihan = ruang.getSesiSpesifik(tanggal, pilsesi);
        if (sesiPilihan.getTersedia()) {
            sesiPilihan.pinjam(pencarianMember);
            return "Sukses! Peminjaman berhasil atas nama: " + pencarianMember.getNama();
        } else {
            return "Maaf, sesi tersebut sudah dipinjam orang lain.";
        }
    }
    // Method pembungkus untuk registrasi member dari GUI
    public String prosesRegistrasiMember(String nama, String nrp) {
        if (nama == null || nama.trim().isEmpty() || nrp == null || nrp.trim().isEmpty()) {
            return "Gagal: Nama dan NRP tidak boleh kosong!";
        }
        
        if (cariMember(nrp) == null) {
            daftarMember.add(new Member(nama, nrp));
            return "Sukses! Member atas nama " + nama + " berhasil didaftarkan.";
        } else {
            return "Gagal: NRP " + nrp + " sudah terdaftar di sistem.";
        }
    }
    
    // Method pembungkus untuk menambah ruangan dari GUI
    public String prosesTambahRuangan(String namaRuang, String kapasitasStr) {
        if (namaRuang == null || namaRuang.trim().isEmpty() || kapasitasStr == null || kapasitasStr.trim().isEmpty()) {
            return "Gagal: Nama ruangan dan kapasitas tidak boleh kosong!";
        }

        try {
            int kapasitas = Integer.parseInt(kapasitasStr);
            if (kapasitas <= 0) {
                return "Gagal: Kapasitas ruangan harus lebih besar dari 0!";
            }

            // Cek duplikat nama ruangan
            for (Ruang r : daftarRuang) {
                if (r.getNamaRuang().equalsIgnoreCase(namaRuang.trim())) {
                    return "Gagal: Ruangan dengan nama tersebut sudah terdaftar!";
                }
            }

            daftarRuang.add(new Ruang(namaRuang.trim(), kapasitas));
            return "Sukses! Ruangan " + namaRuang.trim() + " berhasil ditambahkan.";
        } catch (NumberFormatException e) {
            return "Gagal: Kapasitas harus berupa angka yang valid!";
        }
    }
}

