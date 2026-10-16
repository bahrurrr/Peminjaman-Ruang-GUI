package peminjamanruangpens;
import java.util.HashMap;
import java.util.Map;

/**
 * Kelas Ruang merepresentasikan fasilitas ruangan yang bisa dipinjam.
 * Kelas ini mengatur kapasitas dan pemetaan jadwal harian berdasarkan tanggal.
 * 
 * @author Luthfi Bahrur Rozaq, Bagus Sri Wisnu, Aslam Aji Prasetyo
 * @version 1.0
 */
public class Ruang {
    private String namaRuang;
    private int kapasitas;
    private Map<String, Sesi[]> jadwalPerHari; 

    /**
     * Konstruktor untuk membuat objek Ruangan baru.
     * 
     * @param namaRuang Nama dari fasilitas ruangan.
     * @param kapasitas Jumlah maksimal orang yang bisa ditampung.
     */
    public Ruang(String namaRuang, int kapasitas) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
        this.jadwalPerHari = new HashMap<>();
    }

    /**
     * Mengambil nama ruangan.
     * 
     * @return String nama ruang.
     */
    public String getNamaRuang() {
        return namaRuang;
    }

    /**
     * Mengambil kapasitas maksimal ruangan.
     * 
     * @return int jumlah kapasitas.
     */
    public int getKapasitas() {
        return kapasitas;
    }

    /**
     * *Method private* untuk menginisialisasi 3 sesi default dalam satu hari.
     * 
     * @return Array berisi 3 objek Sesi.
     */
    private Sesi[] buatSesiBaru() {
        Sesi[] sesiBaru = new Sesi[3];
        sesiBaru[0] = new Sesi(1, "08:00 - 10:00");
        sesiBaru[1] = new Sesi(2, "10:00 - 12:00");
        sesiBaru[2] = new Sesi(3, "13:00 - 15:00");
        return sesiBaru;
    }

    /**
     * Mengambil seluruh sesi jadwal pada tanggal tertentu.
     * Jika tanggal tersebut belum memiliki jadwal, sistem akan membuat sesi baru secara otomatis.
     * 
     * @param tanggal String tanggal peminjaman (contoh: "2026-09-23").
     * @return Array dari Sesi yang berisi jadwal hari itu.
     */
    public Sesi[] getJadwalHarian(String tanggal) {
        this.jadwalPerHari.putIfAbsent(tanggal, buatSesiBaru());
        return this.jadwalPerHari.get(tanggal);
    }

    /**
     * Mengambil satu objek Sesi spesifik berdasarkan tanggal dan pilihan nomor sesi.
     * 
     * @param tanggal String tanggal peminjaman.
     * @param pilsesi Nomor urut sesi (1, 2, atau 3).
     * @return Objek Sesi yang dipilih.
     */
    public Sesi getSesiSpesifik(String tanggal, int pilsesi) {
        this.jadwalPerHari.putIfAbsent(tanggal, buatSesiBaru());
        Sesi[] daftarSesi = this.jadwalPerHari.get(tanggal);
        return daftarSesi[pilsesi - 1]; 
    }
}