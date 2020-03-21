package pl.kelog;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ConvertToGrayscaleHandler implements RequestHandler<String, String> {
    
    private static final String IMAGE_TYPE = "jpg";
    
    @Override
    public String handleRequest(String inputBase64, Context context) {
        try {
            return process(inputBase64);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private String process(String inputBase64) throws Exception {
        BufferedImage input = decodeImageBase64(inputBase64);
        BufferedImage result = convertToGrayscale(input);
        
        return encodeImageBase64(result);
    }
    
    private BufferedImage decodeImageBase64(String input64) throws Exception {
        byte[] sourceData = Base64.getDecoder().decode(input64);
        
        return ImageIO.read(new ByteArrayInputStream(sourceData));
    }
    
    private String encodeImageBase64(BufferedImage image) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        ImageIO.write(image, IMAGE_TYPE, stream);
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }
    
    private BufferedImage convertToGrayscale(BufferedImage image) {
        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(colorSpace, null);
        return op.filter(image, null);
    }
}
