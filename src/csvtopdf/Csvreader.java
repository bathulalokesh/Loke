package csvtopdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Csvreader {
	public static void main(String args[]) throws IOException  {
		String path = "C:\\Users\\balokesh\\Downloads\\META_24_06_2022.csv";
		String sourcePdf="C:\\Users\\balokesh\\Downloads\\AWCFPCIM20220622012345601F0123124.pdf";
		String line = "";

		String[] values = {};
		ArrayList<String> list = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				values = line.split(",");
				list.add(values[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 1; i < list.size(); i++) {
			FileInputStream r=new FileInputStream(sourcePdf);
			FileOutputStream w=new FileOutputStream("C:\\Users\\balokesh\\Downloads\\ZIp" + list.get(i));
			
			int j;
			while((j=r.read())!=-1) {
				w.write((char)j);
			}
		}

		
		String sourcePath = "C:\\Users\\balokesh\\Downloads\\ZIp";
		File sourceFile = new File(sourcePath);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\balokesh\\Downloads\\Example.zip");
		ZipOutputStream zos = new ZipOutputStream(fos);
		fetchFileToZip(sourceFile, sourceFile.getName(), zos);
		zos.close();
		fos.close();
	}

	private static void Zipping(File sourceFile, String path2, ZipOutputStream zos) throws IOException {
		FileInputStream fis = new FileInputStream(sourceFile);
		ZipEntry zipEntry = new ZipEntry(path2);
		zos.putNextEntry(zipEntry);
		final byte[] buffer = new byte[1024];
		int len;
		while ((len = fis.read(buffer)) != -1) {
			zos.write(buffer, 0, len);
		}
		fis.close();
	}

	private static void fetchFileToZip(File toZip, String path2, ZipOutputStream zos) throws IOException {
		if (toZip.isDirectory()) {
			File[] files = toZip.listFiles();
			for (File fileName : files) {
				fetchFileToZip(fileName, fileName.getName(), zos);
			}

		} else {
			Zipping(toZip, path2, zos);
		}
	}

	}

