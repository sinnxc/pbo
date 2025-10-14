import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("         Coffee Machine");
        System.out.println("================================");
        while (true) {
            System.out.println("Aksi:\n1.Beli\n2.Isi\n3.Sisa\n4.Lihat Log Transaksi\n5.Keluar\nPilih (1-5):");
            int opsi = scanner.nextInt();
            switch (opsi) {
                case 1:
                    System.out.println("Apa yang ingin Anda beli?\n1.Espresso\n2.Latte\n3.Cappuccino\n4.Moka\n5.Kembali\nPilih (1-4):");
                    int opsiBeli = scanner.nextInt();
                    switch (opsiBeli) {
                        case 1:
                            beliKopi("Espresso", 250, 0, 15, 8000);
                            break;
                        case 2:
                            beliKopi("Latte", 175, 75, 15, 12000);
                            break;
                        case 3:
                            beliKopi("Cappuccino", 150, 100, 15, 11000);
                            break;
                        case 4:
                            beliKopi("Moka", 225, 25, 15, 10000);
                            break;
                        case 5:
                            break;
                    }
                    break;
                case 2:
                    isiStok();
                    break;
                case 3:
                    Stock.tampilkanSisa();
                    break;
                case 4:
                    Log.tampilkanLog();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }

    public static void beliKopi(String jenisKopi, int airDiperlukan, int susuDiperlukan, int bijiKopiDiperlukan, int hargaDasar) {
        System.out.println("Pilih ukuran kopi:\n1.Kecil\n2.Besar");
        int ukuran = scanner.nextInt();
        int faktorUkuran = (ukuran == 1) ? 1 : 2;
        airDiperlukan *= faktorUkuran;
        susuDiperlukan *= faktorUkuran;
        bijiKopiDiperlukan *= faktorUkuran;
        int harga;
        switch (faktorUkuran) {
            case 1:
                harga = hargaDasar;
                break;
            case 2:
                harga = hargaDasar + 3000;
                break;
            default:
                faktorUkuran = 1;
                harga = hargaDasar;
                break;
        }

        System.out.println("Tambahkan gula? (0/1/2/3)");
        char tambahGula = scanner.next().charAt(0);
        switch (tambahGula) {
            case '1':
                if (Stock.gula < 10) {
                    System.out.println("Maaf, gula habis!");
                    Stock.ingatkanAdmin("gula");
                    return;
                }
                Stock.gula -= 10;
                break;
            case '2':
                if (Stock.gula < 20) {
                    System.out.println("Maaf, gula habis!");
                    Stock.ingatkanAdmin("gula");
                    return;
                }
                Stock.gula -= 20;
                harga += 500;
                break;
            case '3':
                if (Stock.gula < 30) {
                    System.out.println("Maaf, gula habis!");
                    Stock.ingatkanAdmin("gula");
                    return;
                }
                Stock.gula -= 30;
                harga += 1000;
                break;
            default:
                break;
        }

        if (Stock.air < airDiperlukan) {
            System.out.println("Maaf, air habis!");
            Stock.ingatkanAdmin("air");
        } else if (Stock.susu < susuDiperlukan) {
            System.out.println("Maaf, susu habis!");
            Stock.ingatkanAdmin("susu");
        } else if (Stock.bijiKopi < bijiKopiDiperlukan) {
            System.out.println("Maaf, biji kopi habis!");
            Stock.ingatkanAdmin("biji kopi");
        } else if (Stock.gelas < 1) {
            System.out.println("Maaf, gelas habis!");
            Stock.ingatkanAdmin("gelas");
        } else {
            System.out.println("Harga: Rp" + harga);
            if (bayar(harga) == 1) {
                Stock.air -= airDiperlukan;
                Stock.susu -= susuDiperlukan;
                Stock.bijiKopi -= bijiKopiDiperlukan;
                Stock.gelas--;
                System.out.println("Silahkan Ambil Kopi Anda!");
                Log.simpanLog(jenisKopi + " (Ukuran: " + (ukuran == 1 ? "Kecil" : "Besar") + ")", harga);
            }
        }
    }

    public static int bayar(int harga) {
        System.out.println("Masukkan uang:");
        int uangMasuk = scanner.nextInt();
        if (uangMasuk >= harga) {
            Stock.saldo += uangMasuk;
            System.out.println("Pembayaran Berhasil!");
            return 1;
        } else {
            System.out.println("Pembayaran Gagal!");
            return 2;
        }
    }

    private static void isiStok() {
        System.out.println("Tambah air (ml):");
        Stock.air += scanner.nextInt();
        System.out.println("Tambah susu (ml):");
        Stock.susu += scanner.nextInt();
        System.out.println("Tambah biji kopi (gram):");
        Stock.bijiKopi += scanner.nextInt();
        System.out.println("Tambah gula (gram):");
        Stock.gula += scanner.nextInt();
        System.out.println("Tambah gelas:");
        Stock.gelas += scanner.nextInt();
        Stock.tampilkanSisa();
    }
}