package peminjamanruangpens;

/**
 * Kelas Sesi merepresentasikan satu blok waktu peminjaman di dalam suatu ruangan.
 * Menyimpan status ketersediaan dan data member yang meminjam.
 * 
 * @author Luthfi Bahrur Rozaq, Bagus Sri Wisnu, Aslam Aji Prasetyo
 * @version 1.0
 */
public class Sesi {
    private int idSesi;
    private String rentangWaktu;
    private boolean tersedia;
    private Member peminjam; 

    /**
     * Konstruktor untuk membuat sesi waktu baru.
     * Secara default, sesi yang baru dibuat berstatus tersedia.
     * 
     * @param idSesi ID urutan sesi (contoh: 1, 2, atau 3).
     * @param rentangWaktu String yang menunjukkan jam mulai dan selesai (contoh: "08:00 - 10:00").
     */
    public Sesi(int idSesi, String rentangWaktu) {
        this.idSesi = idSesi;
        this.rentangWaktu = rentangWaktu;
        this.tersedia = true; 
        this.peminjam = null;
    }
    
    /**
     * Memproses peminjaman pada sesi ini oleh seorang member.
     * Jika sesi tersedia, status akan diubah menjadi tidak tersedia.
     * 
     * @param dataPeminjam Objek Member yang melakukan peminjaman.
     * @return true jika peminjaman berhasil, false jika sesi sudah dipinjam orang lain.
     */
    public boolean pinjam(Member dataPeminjam) {
        if (this.tersedia == true) {
            this.tersedia = false;
            this.peminjam = dataPeminjam;
            return true;
        }
        return false;
    }

    /**
     * Mengecek ketersediaan sesi.
     * 
     * @return true jika belum dipinjam, false jika sudah dipinjam.
     */
    public boolean getTersedia() {
        return tersedia;
    }

    /**
     * Mengambil rentang waktu dari sesi ini.
     * 
     * @return String rentang waktu.
     */
    public String getWaktuSesi() {
        return rentangWaktu;
    }
    
    /**
     * Mengambil status lengkap dari sesi, termasuk nama peminjam jika sudah dipinjam.
     * 
     * @return String informasi status ketersediaan sesi.
     */
    public String getStatus() {
        if (this.tersedia == false && this.peminjam != null) {
            return "Sesi " + this.idSesi + " (" + this.rentangWaktu + ") - Dipinjam oleh: " + this.peminjam.getNama() + " (NRP: " + this.peminjam.getNrp() + ")";
        }
        return "Sesi " + this.idSesi + " (" + this.rentangWaktu + ") - Tersedia";
    }
}