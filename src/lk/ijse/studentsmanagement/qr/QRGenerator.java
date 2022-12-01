package lk.ijse.studentsmanagement.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenerator{
    public static void getGenerator(String id) throws IOException, WriterException {
        String path = "/home/shan/Desktop/StudentsManagementSystem/src/lk/ijse/registrationQRCodes"+id+".png";
        BitMatrix encode = new MultiFormatWriter().encode(id, BarcodeFormat.QR_CODE, 200, 200);
        Path path1 = Paths.get(path);
        MatrixToImageWriter.writeToPath(encode,path.substring(path.lastIndexOf('.')+1), path1);
//        Stage stage = new Stage();
//        ImageView imageView = new ImageView();
//        //Setting image to the image view
//        imageView.setImage(new Image(Files.newInputStream(path1)));
//        imageView.setFitWidth(575);
//        imageView.setPreserveRatio(true);
//        //Setting the Scene object
//        Group root = new Group(imageView);
//        Scene scene = new Scene(root, 595, 370);
//        stage.setTitle("QR - "+id);
//        stage.setScene(scene);
//        stage.show();
    }
}
