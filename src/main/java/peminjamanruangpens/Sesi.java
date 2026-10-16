package peminjamanruangpens;

public class Sesi {
    private int idSesi;
    private String rentangWaktu;
    private boolean tersedia;
    private Member peminjam; 

    public Sesi(int idSesi, String rentangWaktu) {
        this.idSesi = idSesi;
        this.rentangWaktu = rentangWaktu;
        this.tersedia = true; 
        this.peminjam = null;
    }
    
    public boolean pinjam(Member dataPeminjam) {
        if (this.tersedia == true) {
            this.tersedia = false;
            this.peminjam = dataPeminjam;
            return true;
        }
        return false;
    }

    public boolean getTersedia() {
        return tersedia;
    }

    public String getWaktuSesi() {
        return rentangWaktu;
    }
    
    public String getStatus() {
        if (this.tersedia == false && this.peminjam != null) {
            return "Sesi " + this.idSesi + " (" + this.rentangWaktu + ") - Dipinjam oleh: " + this.peminjam.getNama() + " (NRP: " + this.peminjam.getNrp() + ")";
        }
        return "Sesi " + this.idSesi + " (" + this.rentangWaktu + ") - Tersedia";
    }
}