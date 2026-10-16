package peminjamanruangpens;
import java.util.HashMap;
import java.util.Map;

public class Ruang {
    private String namaRuang;
    private int kapasitas;
    private Map<String, Sesi[]> jadwalPerHari; 

    public Ruang(String namaRuang, int kapasitas) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
        this.jadwalPerHari = new HashMap<>();
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    private Sesi[] buatSesiBaru() {
        Sesi[] sesiBaru = new Sesi[3];
        sesiBaru[0] = new Sesi(1, "08:00 - 10:00");
        sesiBaru[1] = new Sesi(2, "10:00 - 12:00");
        sesiBaru[2] = new Sesi(3, "13:00 - 15:00");
        return sesiBaru;
    }

    public Sesi[] getJadwalHarian(String tanggal) {
        this.jadwalPerHari.putIfAbsent(tanggal, buatSesiBaru());
        return this.jadwalPerHari.get(tanggal);
    }


    public Sesi getSesiSpesifik(String tanggal, int pilsesi) {
        this.jadwalPerHari.putIfAbsent(tanggal, buatSesiBaru());
        Sesi[] daftarSesi = this.jadwalPerHari.get(tanggal);
        return daftarSesi[pilsesi - 1]; 
    }
}