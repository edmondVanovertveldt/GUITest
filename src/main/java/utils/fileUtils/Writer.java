package utils.fileUtils;

import java.io.FileWriter;
import java.io.IOException;

/** Classe utilitaire de la gestion des fichiers
 * Created by edmondvanovertveldt on 04/01/15.
 */
public class Writer {

    /**
     * Cree un nouveau fichier
     * @param file, nm du fichier à creer
     * @throws IOException
     */
    public static void createFile(String file) throws IOException {
        java.io.File newfile = new java.io.File(file);

        newfile.createNewFile();
    }

    /**
     * Ecrit dans un fichier un contenu
     * @param file le fichier
     * @param content le contenu
     * @throws IOException
     */
    public static void writeContentInFile(String file, String content ) throws IOException {
        FileWriter writer = null;
        try{
            writer = new FileWriter(file, true);
            writer.write(content,0,content.length());
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            if(writer != null) {
                writer.close();
            }
        }
    }
}
