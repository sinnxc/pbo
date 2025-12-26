| Nama | NRP |
| --- | --- |
| Ahsin Khuluqil Karim | 5025241063 |
| Naufal Daffa Alfa Zain | 5025241066 | 
| Royan Habibi Alfatih | 5025241115 |

## Game Ninja Heroes (Naruto) Sederhana Berbasis JavaFX

### 1. Deskripsi
Proyek ini adalah sebuah permainan strategi _turn-based_ (berbasis giliran) sederhana yang terinspirasi dari anime Naruto dan game mobile "Ninja Heroes". Aplikasi ini dibangun menggunakan bahasa pemrograman Java dan pustaka JavaFX untuk antarmuka pengguna (GUI).

Dalam game ini, pemain dapat memilih tim yang terdiri dari berbagai karakter ninja populer (seperti Naruto, Sasuke, Boruto, Madara, dll), lalu bertarung melawan tim lawan di arena. Proyek ini dibuat untuk mendemonstrasikan penerapan konsep Pemrograman Berorientasi Objek (OOP), manajemen aset (gambar dan suara), serta penggunaan GUI modern di Java.

Fitur Utama:
- Pemilihan Karakter: Terdapat puluhan karakter dari era Naruto Shippuden hingga Boruto.
- Sistem Pertarungan: Logika _turn-based_ di mana setiap ninja memiliki giliran untuk menyerang atau menggunakan skill.
- Aset Visual & Audio: Menggunakan _sprite_ karakter, banner, latar belakang arena Konoha, dan musik latar.

### 2. Rancangan Kelas
Struktur kode program dibagi menjadi beberapa kelas utama yang saling berinteraksi:
- MainGUI: Kelas utama (Main Class) yang berfungsi sebagai _entry point_ aplikasi dan menginisialisasi _Stage_ JavaFX.
- Hero: Kelas model yang merepresentasikan data dasar setiap ninja (Nama, HP, Attack, Defense, Speed).
- HeroFactory: Menerapkan _Factory Pattern_ untuk mempermudah pembuatan objek Hero berdasarkan nama karakter tanpa perlu membuat kelas baru untuk setiap ninja.
- Skill & SkillType: Mengatur logika jurus/kemampuan khusus karakter, termasuk tipe serangannya.
- PlayerAction: Menangani input dan aksi yang dipilih oleh pemain.
- BattleHeroState: Menyimpan status dinamis hero saat pertarungan berlangsung (misalnya: sisa HP saat ini, status _stun_, atau _dead_).
- gui/HeroSelectionScreen: Menangani tampilan antarmuka saat pemain memilih anggota tim sebelum bertarung.
- gui/BattleScreen: Menangani tampilan utama pertarungan, merender _background_ arena, dan _sprite_ karakter.
- TeamBattleSystemGui: Mengatur logika inti dari sistem pertarungan tim.

### 3. Gambar Aplikasi dan Penjelasan
- Halaman Pemilihan Karakter (Hero Selection)  
  ![Halaman Pemilihan Karakter](https://blogger.googleusercontent.com/img/a/AVvXsEhj5maGa12y5MeP8tGApAKGDDSgW9DJ621-2JFdtAF4FoDvYTNy3QaUpJ0Do868RDjjtjb23N9ImiC6B6lyVjfHyORXg8jtqs4NIW2zAzzB0HjMLLMaJc_s6FD49TDXGzcngY6KQ_ykZUrs_zL6mwWpnhpbUE5TWGAn1rg2aSn6Ql6xYIRbbvWyv4CmspVy=w640-h360)  
  Penjelasan: Pada halaman ini, pemain disuguhi daftar karakter (Banner) yang tersedia di folder assets. Pemain dapat memilih 3 ninja untuk dimasukkan ke dalam tim mereka, sementara 3 hero lawan dipilih secara random.

- Halaman Pertarungan (Battle Arena)  
  ![Halaman Pertarungan](https://blogger.googleusercontent.com/img/a/AVvXsEjXYG00XQUc4jlcPYbsKlrwVTuKe3pm-h8qbW2cL6abXEzcVX_g3sLKH9-84tJN4PlAaBNF57Ng25Hs2EWVw9aKIliZOsP8ig-3HcGgqVptE4VV_RF9ihUHjmincyn8uHXTQLG5wR1G1W6TlJ-RIs_X1Ac-OkT2-bD84wI_TBkDO1UqH7-EisBe_eMICt44=w640-h360)  
  Penjelasan: Ini adalah tampilan utama permainan. Di sisi kiri adalah tim pemain dan sisi kanan adalah tim lawan. Sprite karakter dirender dengan latar belakang 'Konoha Arena'. Bar darah (HP) ditampilkan di atas setiap karakter.

  Dalam fase pertarungan, pemain memegang kendali taktis dengan memilih satu dari tiga opsi aksi: Basic Attack, Hero Skill, atau Clan Skill. Sebaliknya, algoritma musuh (AI) beroperasi secara stokastik, menentukan langkah berdasarkan persentase probabilitas aksi.

  Setiap skill diklasifikasikan ke dalam tiga tipe, Attack, Heal, dan Defense, yang diatur oleh mekanisme Cooldown. Durasi cooldown berbanding lurus dengan kekuatan skill, semakin kuat dampaknya, semakin lama waktu tunggunya. Permainan menerapkan sistem eliminasi beruntun (tag-team), di mana karakter yang kehabisan HP akan otomatis digantikan oleh karakter berikutnya. Pertarungan berakhir ketika salah satu tim kehilangan seluruh (3) anggotanya.

- Tampilan Saat Permainan Berakhir  
  ![Tampilan Saat Permainan Berakhir](https://blogger.googleusercontent.com/img/a/AVvXsEjm2mnk8b798O6HNo4_WagTaXVT5Y4loldPIQMkeh8Ht03kzF6geTaztZO1xEIL_42eJR0D0GMWVRuAffi7PD3pZmYJTa_8DqOYaCOtlQwf5pJI0OuYqdUHv1mUw61rbGv4qEmpndUnQp3APUXYT1zAPUl_3rD8hxco_DT1TwuZFcPCGthpf4VoyQjV3Ct_=w640-h360)  
  Penjelasan: Contoh tampilan permainan berakhir dan pemenang ditentukan.
