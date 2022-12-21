package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;


public class Picture extends ImageView {

	 private String sourcePath;

	 /**
	  * Construtor of the picture.
	  * @param sourcePath  Path of the picture use in the class.
	  * @throws Exception  If the path is wrong then a exception is needed. 
	  */
	 public Picture(String sourcePath) throws Exception{
		 super(new Image(new FileInputStream("src/" + sourcePath)));
		 this.sourcePath = sourcePath;
	 }

	 // Getter :
	 
	 public String getSourcePath() {
		 String source = this.sourcePath;
		 return source;
	 }
	 
}
