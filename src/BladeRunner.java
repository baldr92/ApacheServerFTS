import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Random;

import javax.print.attribute.IntegerSyntax;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.naming.java.javaURLContextFactory;

public class BladeRunner {

	public static void main(String args[]) throws ParseException {
		
		
		//String link = "";
		//XMLCreator xml = new  XMLCreator();
		//xml.chooseXML(link);		
				

		//ListenerMQMsg listener = new ListenerMQMsg();
		//listener.getMessageFromMq();
		
		//CollectionsXML xml = new CollectionsXML();
		//xml.operation1();
		
		//Random rnd = new Random();
		//int n = 10000000 + rnd.nextInt(90000000);
		//System.out.println(n);
		//BladeRunner br = new BladeRunner(); 
		//br.readAndCreateList();
		//XMLCreator xml = new XMLCreator();
		//System.out.println(xml.generatorVIN());
		/*
		Random random = new  Random();
		String eng = "abcdefghjiklmopqrstvwzyq";

		String sum = rus + rus.toUpperCase() + eng + eng.toUpperCase();
		char a = sum.charAt(random.nextInt(sum.length()));
		char b = sum.charAt(random.nextInt(sum.length()));
		char c = sum.charAt(random.nextInt(sum.length()));
		char d = sum.charAt(random.nextInt(sum.length()));
		String qwe = String.valueOf(a+b+c+d);
		System.out.println(qwe);
		*/
		
		/*
			double max = 1000.0;
			double weightRandom = Math.random()*max;			
			String formattedWeight = String.format("%.1f", weightRandom);
			System.out.println(formattedWeight);*/
		/*
		Random random = new Random();
		int minDay = (int) LocalDate.of(2018, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2018, 6, 30).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay-minDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		String ranDate = randomDate.toString();
		System.out.println(ranDate);
		*/
		
		BladeRunner bladeRunner = new BladeRunner();
		bladeRunner.readAndCreateList();
	}
	
	
	public String randYear() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String date = sf.format(new Date());
		return date;
	}
	
	public void random1() {
		System.out.print(RandomStringUtils.randomAlphabetic(1));
	}
	
	public StringBuilder randomNumber() {
		final String CHARS = "123456789";
		final int STR_LENGTH = 25;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);			
			builder.append(ch);
		}
		return builder;
	}
	
	public String randTimeNow() {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String time = sf.format(date.getTime());
		return time;
	}
	
	public String randomPiRegID() {
		int num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12;
		int num13, num14, num15, num16, num17, num18, num19, num20, num21, num22;
		Random rnd = new Random();
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		num7 = rnd.nextInt(10);
		num8 = rnd.nextInt(10);
		num9 = rnd.nextInt(10);
		num10 = rnd.nextInt(10);
		num11 = rnd.nextInt(10);
		num12 = rnd.nextInt(10);
		num13 = rnd.nextInt(10);
		num14 = rnd.nextInt(10);
		num15 = rnd.nextInt(10);
		num16 = rnd.nextInt(10);
		num17 = rnd.nextInt(10);
		num18 = rnd.nextInt(10);
		num19 = rnd.nextInt(10);
		num20 = rnd.nextInt(10);
		num21 = rnd.nextInt(10);
		num22 = rnd.nextInt(10);
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+"-"+num9+""+num10+"-"+num12+""+num13+""+num14+""+num15+""+num16+""+num17+"-"+num18+""+num19+""+num20+""+num21+"-"+num22;
		return s;
	}
	
	public String randDate() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		//SimpleDateFormat formatForDb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String date = simpleFormat.format(new Date());
		return date;
	}
	
	public StringBuilder generatorVIN() {
		final String CHARS = "";
		final int STR_LENGTH = 5;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);
			builder.append(ch);
		}
		/*Random rnd = new Random();
		int num1, num2, num3;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		String s = builder.toString() + ""+num1+""+num2+""+num3;*/
		System.out.println(builder);
		return builder;
	}
	
	public void readAndCreateList() {
		try {
			//FileInputStream fs = new FileInputStream("C:\\Users\\Obesp\\Desktop\\cities.txt");
			File myFile = new File("C:\\Users\\Obesp\\Desktop\\worknodes.csv");
			FileReader fr = new FileReader(myFile);
			BufferedReader bf = new BufferedReader(fr);
			FileWriter writer = new FileWriter("worknodesList.csv");
			BufferedWriter bw  = new BufferedWriter(writer);
			String line;
			while ( (line = bf.readLine()) != null){
				
				String newline ="\"" + line+ "\",";
				
				bw.write(newline);
				
				
			}
			System.out.println("Success");
			bw.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 int num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11; 
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		num7 = rnd.nextInt(10);
		num8 = rnd.nextInt(10);
		num9 = rnd.nextInt(10);
		num10 = rnd.nextInt(10);
		num11 = rnd.nextInt(10);
		num12 = rnd.nextInt(10);
		num13 = rnd.nextInt(10);
		num14 = rnd.nextInt(10);
		num15 = rnd.nextInt(10);
		num16 = rnd.nextInt(10);
		num17 = rnd.nextInt(10);
		num18 = rnd.nextInt(10);
		num19 = rnd.nextInt(10);
		num20 = rnd.nextInt(10);
		num21 = rnd.nextInt(10);
		
	 */

}
