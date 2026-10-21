# CampusTasks — Kotlin Domain Model

## Repository
- **Repository:** https://github.com/JoJo-do/CampusTasks
- **Branch:** `assignment/kotlin-domain-240605110192`

## Deskripsi
CampusTasks adalah implementasi domain model menggunakan Kotlin untuk merepresentasikan dan mengelola tugas. Proyek ini berfokus pada validasi data, penggunaan immutable data class, serta operasi pencarian dan pengelompokan tugas.

## Fitur
- `Task`: model tugas dengan ID, judul, deskripsi opsional, prioritas, status, estimasi waktu, dan tag.
- `TaskPriority` dan `TaskStatus`: enum untuk prioritas dan status tugas.
- `Identifiable`: interface untuk objek yang memiliki ID.
- `TaskValidator`: validasi dan normalisasi judul, deskripsi, dan tag.
- `TaskFactory`: pembuatan tugas dengan hasil sukses atau daftar kesalahan.
- `TaskQueries`: pencarian, filter, pengelompokan, dan pencarian berdasarkan ID.

## Aturan Domain
- Judul tugas harus memiliki panjang 3–80 karakter setelah spasi di awal dan akhir dihapus.
- Estimasi waktu pengerjaan harus berada di antara 5–480 menit.
- Deskripsi boleh kosong atau `null`; deskripsi kosong setelah normalisasi menjadi `null`.
- Tag dinormalisasi dengan menghapus spasi di awal dan akhir. Tag kosong tidak diperbolehkan.
- Status awal tugas adalah `OPEN`.
- Perubahan status menghasilkan objek baru menggunakan `copy()`, sehingga objek awal tidak berubah.

## Menjalankan Pengujian
Jalankan perintah berikut dari terminal di folder utama proyek:

```powershell
.\gradlew testDebugUnitTest
```

Pengujian unit mencakup validasi data, pembuatan tugas, serta operasi query.

## Refleksi
Dalam pengerjaan proyek ini, saya mempelajari bagaimana Kotlin dapat digunakan untuk membangun domain model yang aman dan mudah dipelihara. Penggunaan `data class` membantu merepresentasikan data tugas secara ringkas, sedangkan `val` dan fungsi `copy()` mendukung pendekatan immutable sehingga perubahan status tidak mengubah objek asli.

Saya juga memahami pentingnya validasi data sebelum objek dibuat. `TaskValidator` bertanggung jawab memeriksa dan menormalisasi input, sementara `TaskFactory` menangani proses pembuatan tugas dan mengembalikan hasil berupa sukses atau daftar kesalahan. Pemisahan tanggung jawab ini membuat kode lebih terstruktur dan mudah diuji.

Selain itu, extension function pada `TaskQueries` memudahkan proses filter, pencarian berdasarkan kata kunci, pengelompokan berdasarkan status, dan pencarian berdasarkan ID. Pengujian unit membantu memeriksa kasus normal maupun batas validasi, sehingga kesalahan dapat ditemukan lebih awal. Ke depannya, saya ingin meningkatkan kualitas implementasi dengan menambahkan pengujian untuk setiap perilaku domain dan menjaga agar dokumentasi tetap sesuai dengan kode yang sebenarnya.
