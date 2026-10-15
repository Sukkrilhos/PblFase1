import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GerenciadorDeAudio {

    // Método para tocar um som curto (efeitos sonoros)
    public void tocarSom(String caminhoArquivo) {
        try {
            File arquivoAudio = new File(caminhoArquivo);
            if (arquivoAudio.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(arquivoAudio);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start(); // Toca o som em segundo plano
            } else {
                // Comentado para não sujar o terminal caso falte algum som
                // System.out.println("(Aviso: Som não encontrado - " + caminhoArquivo + ")");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Erro ao reproduzir áudio: " + e.getMessage());
        }
    }
}