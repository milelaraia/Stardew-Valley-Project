package Util;

public class RelogioJogo extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("⏳ O tempo está passando em Pelican Town...");
                Thread.sleep(60000); // 60 segundos em milissegundos
            }
        } catch (InterruptedException e) {
            System.out.println("Relógio interrompido.");
        }
    }
}
