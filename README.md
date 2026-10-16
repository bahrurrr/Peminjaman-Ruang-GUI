# 🏢 Sistem Peminjaman Ruangan PENS (JavaFX GUI)

Aplikasi *desktop* berbasis antarmuka grafis (GUI) untuk memanajemen dan mempermudah proses peminjaman ruangan di kampus PENS. Sistem ini dibangun menggunakan bahasa Java dengan *framework* JavaFX, menerapkan konsep Pemrograman Berorientasi Objek (OOP) secara penuh, dan menggunakan struktur data *in-memory* (`LinkedList` & `HashMap`) untuk penyimpanan datanya.

## ✨ Fitur Utama
*   **Lihat Jadwal & Pinjam Ruangan:** Mengecek ketersediaan 3 sesi (08:00 - 15:00) pada tanggal tertentu untuk setiap ruangan, serta melakukan peminjaman menggunakan validasi NRP.
*   **Registrasi Member Baru:** Mendaftarkan peminjam baru ke dalam sistem agar bisa melakukan peminjaman.
*   **Tambah Ruangan Baru:** Menambahkan fasilitas ruangan baru beserta kapasitasnya secara dinamis.
*   **Dukungan Dual-Interface:** Sistem inti (`SistemPeminjaman.java`) memiliki fleksibilitas untuk dijalankan via *Command Line Interface* (CLI) maupun dipanggil melalui antarmuka GUI (`Main.java`).

## 💻 Tech Stack
*   **Bahasa Pemrograman:** Java
*   **GUI Framework:** JavaFX
*   **Struktur Data (Penyimpanan):** `LinkedList` (untuk daftar ruang & member) dan `HashMap` (untuk pemetaan jadwal harian). *Catatan: Data bersifat sementara (volatile) dan akan ter-reset saat aplikasi ditutup karena tidak menggunakan database eksternal.*

## 📂 Struktur File (Arsitektur OOP)
1.  `Main.java` - Kelas utama yang mengatur *routing* antar-halaman GUI dan inisialisasi JavaFX.
2.  `SistemPeminjaman.java` - Kelas *controller* pusat yang memproses logika bisnis, validasi, dan manipulasi data.
3.  `Ruang.java` - Model objek ruangan yang menyimpan nama, kapasitas, dan jadwal harian.
4.  `Sesi.java` - Model objek sesi waktu (rentang jam) yang menyimpan status ketersediaan dan data peminjam.
5.  `Member.java` - Model objek pengguna yang menyimpan nama dan NRP.

## 🚀 Persiapan & Cara Menjalankan (Prerequisites)

Karena aplikasi ini menggunakan JavaFX, pastikan sistem komputermu sudah memiliki kelengkapan berikut:
1.  **Java Development Kit (JDK):** Versi 11 ke atas.
2.  **IDE yang Mendukung JavaFX:** Sangat disarankan menggunakan IntelliJ IDEA, Eclipse, atau VS Code dengan ekstensi *Java Extension Pack*.

### Langkah Menjalankan:
1.  **Clone Repository:**
    ```bash
    git clone [https://github.com/bahrurrr/Peminjaman-Ruang-GUI.git](https://github.com/bahrurrr/Peminjaman-Ruang-GUI.git)
    cd Peminjaman-Ruang-GUI
    ```
2.  **Buka di IDE:** Buka *folder* proyek ini di IDE favoritmu.
3.  **Konfigurasi JavaFX (Jika diperlukan):** Pastikan modul JavaFX (`javafx.controls`, `javafx.graphics`) sudah terhubung ke dalam *build path* atau `module-info.java` milikmu.
4.  **Run Aplikasi:** Buka file `Main.java` dan jalankan (Run).
5.  *(Opsional)*: Terdapat *dummy data* yang sudah otomatis dimuat saat sistem berjalan, yaitu ruangan "Lab APD", "Lab Jaringan", "Ruang B201", dan pengguna "Luthfi Bahrur Rozaq" (NRP: 069) untuk keperluan *testing*.

## 📸 *Screenshots* Aplikasi
> <img width="527" height="513" alt="image" src="https://github.com/user-attachments/assets/683fdb25-96ce-42c6-8dd9-d96360bb682f" />
> <img width="565" height="788" alt="image" src="https://github.com/user-attachments/assets/e7d8bf61-b8fe-49a5-82c1-435ead303355" />


## 👨‍💻 Author
**Luthfi Bahrur Rozaq**  
**Bagus Sri Wisnu**  
**Aslam Aji Prasetyo**

*   GitHub: [@bahrurrr](https://github.com/bahrurrr)
