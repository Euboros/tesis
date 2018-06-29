
package tesis29jn;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class Tesis29jn {

    private static void Convertir(int i) {
        for(int t =0;t<i;t++){
        Imgcodecs imageCodecs = new Imgcodecs();
        String input = "C:\\Users\\Euboros\\Desktop\\lololo\\photo"+t+".jpg";
         Mat src = imageCodecs.imread(input, Imgcodecs.IMREAD_GRAYSCALE);
         byte[] data1 = new byte[src.rows() * src.cols() * (int)(src.elemSize())];
         src.get(0, 0, data1);
         BufferedImage bufImage = new BufferedImage(src.cols(),src.rows(), 
         BufferedImage.TYPE_BYTE_GRAY);
         bufImage.getRaster().setDataElements(0, 0, src.cols(), src.rows(), data1);
         imageCodecs.imwrite(input, src);
    }
    
    
    }

    

 

    private Mat matrix=null;

 
            
            
    public WritableImage  capture() {
      
      WritableImage WritableImage = null;
      // Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
      // Instantiating the VideoCapture class (camera:: 0)
      VideoCapture capture = new VideoCapture(0);
      // Reading the next video frame from the camera
      Mat matrix = new Mat();
      capture.read(matrix);

      // If camera is opened
      if( capture.isOpened()) {
         // If there is next video frame
         if (capture.read(matrix)) {
            // Creating BuffredImage from the matrix
            BufferedImage image = new BufferedImage(matrix.width(),matrix.height(), BufferedImage.TYPE_BYTE_GRAY);
            WritableRaster raster = image.getRaster();
            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = dataBuffer.getData();
            matrix.get(0, 0, data);
            this.matrix = matrix;
            // Creating the Writable Image
            WritableImage = SwingFXUtils.toFXImage(image, null);
         }
      }
      else{System.out.println("error camara no encontrada");};
        capture.release();
        return WritableImage;

    }
    
    public void saveImage(int i) {
      // Generar nombre archivo
      String file = "C:\\Users\\Euboros\\Desktop\\lololo\\photo"+i+".jpg";
      // Instantiating the imgcodecs class
      Imgcodecs imageCodecs = new Imgcodecs();
      // guardando archivo
      imageCodecs.imwrite(file, matrix);
      
      
   }
    
    
    public static void main(String[] args) {
      int i=0;
      for(i =0;i<10;i++){
        
        Tesis29jn obj = new Tesis29jn();
        
        WritableImage writableImage = obj.capture(); 
        obj.saveImage(i);  
        System.out.println("listoko"+i);
        
    }
    Convertir(i);
    System.out.println("wololo");
    System.exit(0);
}
}