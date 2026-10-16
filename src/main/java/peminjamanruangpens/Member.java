package peminjamanruangpens;

/**
 * Kelas Member merepresentasikan data pengguna (mahasiswa) 
 * yang terdaftar dalam sistem dan berhak melakukan peminjaman ruangan.
 * 
 * @author Luthfi Bahrur Rozaq, Bagus Sri Wisnu, Aslam Aji Prasetyo
 * @version 1.0
 */
public class Member {
    private String nama;
    private String nrp;

    /**
     * Konstruktor untuk membuat objek Member baru.
     * 
     * @param nama Nama lengkap dari member yang mendaftar.
     * @param nrp Nomor Registrasi Pokok (3 digit belakang).
     */
    public Member(String nama, String nrp) {
        this.nama = nama;
        this.nrp = nrp;
    }

    /**
     * Mengambil nama lengkap dari member.
     * 
     * @return String berupa nama member.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Mengambil NRP dari member.
     * 
     * @return String berupa NRP member.
     */
    public String getNrp() {
        return nrp;
    }
}