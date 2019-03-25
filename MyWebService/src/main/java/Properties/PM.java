package Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PM {
	
	public static Properties pps;
	static {
		pps = new Properties();
	}
	
	public PM(String path) {
		try {
			pps = new Properties();
			// 使用InPutStream流读取properties文件
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			pps.load(bufferedReader);
		} catch (IOException e) {
			e.printStackTrace();
			//System.exit(1);
		}
	}
}
