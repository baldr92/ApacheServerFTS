
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
//import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import org.apache.catalina.ha.backend.Sender;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.*;

public class XMLCreator{
	/*public static void main (String args[]) {
		XMLCreator xml = new XMLCreator();
		xml.selectXML();
	}*/

	public String date;
	public String dateDb;
	public UUID uuid1;
	public UUID envId;
	public  java.util.regex.Pattern patEnvID;
	public String typeXml;
	public String modifyXML;



	public String chooseXML(String link) throws ParseException, Exception {
		//String sourceXML = getStringFromResurce(link);
		this.typeXml = link;

		SenderToMQ sender = new SenderToMQ();

		if (link.contains("I309")) {
			transformateXML1();
		}
		if (link.contains("I312")) {
			transformateXML2();
		}
		if (link.contains("I305")) {
			transformateXML3();
		}
		if (link.contains("I308")) {
			transformateXML4();
		}
		if (link.contains("I416")) {
			transformateXML5();
		}
		if (link.contains("I422")) {
			transformateXML6();
		}
		if (link.contains("I424")) {
			transformateXML7();
		}
		if (link.contains("I425")) {
			transformateXML8();
		}
		if (link.contains("I418")) {
			transformateXML9();
		}
		if (link.contains("I420")) {			// в лоад раннере прописать как ЗДЕСЬ!
			transformateXML10();
		}
		if (link.contains("I403")) {				// в лоад раннере прописать как ЗДЕСЬ!
			transformateXML11();
		}
		if (link.contains("I405")) {
			transformateXML12();
		}
		sender.createQueue(this);
		return this.modifyXML;
	}


	public String transformateXML1()  {
		String Cnfrm = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\" xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"  <Header>\r\n" +
				"    <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\">\r\n" +
				"      <EnvelopeID>b0744f43-c17f-483d-bb66-521ata401788</EnvelopeID>\r\n" +
				"<SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</SenderInformation>\r\n" +
				"<ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</ReceiverInformation>\r\n" +
				"      <PreparationDateTime>2017-03-07T15:21:19</PreparationDateTime>\r\n" +
				"    </RoutingInf>\r\n" +
				"    <ApplicationInf xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\">\r\n" +
				"      <MessageKind>I309</MessageKind>\r\n" +
				"    </ApplicationInf>\r\n" +
				"    <ATCompTypes:ATHeader>\r\n" +
				"      <ATCompTypes:EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"      <ATCompTypes:ToCustomsCode>10000000</ATCompTypes:ToCustomsCode>\r\n" +
				"      <ATCompTypes:ToCustomsSystem>0</ATCompTypes:ToCustomsSystem>\r\n" +
				"      <ATCompTypes:FromCustomsCode>10209000</ATCompTypes:FromCustomsCode>\r\n" +
				"      <ATCompTypes:FromCustomsSystem>16</ATCompTypes:FromCustomsSystem>\r\n" +
				"    </ATCompTypes:ATHeader>\r\n" +
				"  </Header>\r\n" +
				"  <Body>\r\n" +
				"    <TTinf xmlns=\"urn:customs.ru:Information:AutoTransport:ATttTypes:10.0\"\r\n" +
				"           xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\"\r\n" +
				"           xmlns:ATSimpTypes=\"urn:customs.ru:Information:AutoTransport:ATSimpTypes:10.0\"\r\n" +
				"           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"      <CustomsSystemType>16</CustomsSystemType>\r\n" +
				"      <Type>309</Type>\r\n" +
				"      <Number>10209001/070311/00000741</Number>\r\n" +
				"      <CustomsCode>10209000</CustomsCode>\r\n" +
				"      <CreateDateTime>2018-05-07T09:35:50.0000000+03:00</CreateDateTime>\r\n" +
				"      <DeliveryType>2</DeliveryType>\r\n" +
				"      <DeliveryDate>2018-05-22T00:00:00.0000000+03:00</DeliveryDate>\r\n" +
				"      <DeliveryFactCustomsCode>10200000</DeliveryFactCustomsCode>\r\n" +
				"      <DeliveryFactDate>2012-05-22T00:00:00.0000000+03:00</DeliveryFactDate>\r\n" +
				"      <BlankNumber>00000745</BlankNumber>\r\n" +
				"      <BlankIssuedDate>2017-03-07T00:00:00.0000000+03:00</BlankIssuedDate>\r\n" +
				"      <DeliveryCustomsCode>10207000</DeliveryCustomsCode>\r\n" +
				"      <StartDate>2017-03-07T00:00:00.0000000+03:00</StartDate>\r\n" +
				"      <StartCustomsCode>10209000</StartCustomsCode>\r\n" +
				"      <Comment>Comment</Comment>\r\n" +
				"      <VersionDateTime>2017-03-07T09:36:09.0000000+03:00</VersionDateTime>\r\n" +
				"      <DocSendDate>2017-03-07T09:35:50.0000000+03:00</DocSendDate>\r\n" +
				"      <ApplicationName>ASKTT2</ApplicationName>\r\n" +
				"      <ApplicactionVersion>2.11</ApplicactionVersion>\r\n" +
				"      <AttachedVehicles>\r\n" +
				"        <LinkedVeh>\r\n" +
				"          <VehicleRole>4</VehicleRole>\r\n" +
				"          <VehicleOrderNumber>1</VehicleOrderNumber>\r\n" +
				"          <PrevGoodsItem>\r\n" +
				"            <ATCompTypes:PrevDocNumber>PrevDocNumber</ATCompTypes:PrevDocNumber>\r\n" +
				"            <ATCompTypes:PrevDocType>312</ATCompTypes:PrevDocType>\r\n" +
				"            <ATCompTypes:PrevGoodsNumber>123</ATCompTypes:PrevGoodsNumber>\r\n" +
				"          </PrevGoodsItem>\r\n" +
				"          <GoodsItem>\r\n" +
				"            <ATCompTypes:GoodsName>GoodsName</ATCompTypes:GoodsName>\r\n" +
				"            <ATCompTypes:GoodsNumber>123</ATCompTypes:GoodsNumber>\r\n" +
				"          </GoodsItem>\r\n" +
				"          <Vehicle>\r\n" +
				"            <StateSignID>ata6555931K171000</StateSignID>\r\n" +
				"            <VINID>ata6555931K171000</VINID>\r\n" +
				"            <TNVEDCode>8703231910</TNVEDCode>\r\n" +
				"            <Type>20</Type>\r\n" +
				"            <MarkCode>79</MarkCode>\r\n" +
				"            <Model>XX</Model>\r\n" +
				"            <OfftakeYear>2008</OfftakeYear>\r\n" +
				"            <EngineID>0987631</EngineID>\r\n" +
				"            <ChassisID>ata6555931K171000</ChassisID>\r\n" +
				"            <BodyID>ata6555931K171000</BodyID>\r\n" +
				"            <CabinID>ata6555931K171000</CabinID>\r\n" +
				"            <NationalityCode>100</NationalityCode>\r\n" +
				"            <FromCountryCode>860</FromCountryCode>\r\n" +
				"            <OwnDocNames>OwnDocNames</OwnDocNames>\r\n" +
				"            <IdentDocNames>IdentDocNames</IdentDocNames>\r\n" +
				"            <FStoim>11111</FStoim>\r\n" +
				"            <FSValKod>RUB</FSValKod>\r\n" +
				"            <Owner>\r\n" +
				"              <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"              <ATCompTypes:OrganizationName>OrganizationName</ATCompTypes:OrganizationName>\r\n" +
				"              <ATCompTypes:IdentityDocCode>10</ATCompTypes:IdentityDocCode>\r\n" +
				"              <ATCompTypes:IdentityDocNumber>123456</ATCompTypes:IdentityDocNumber>\r\n" +
				"              <ATCompTypes:IdentityDocSeries>1231</ATCompTypes:IdentityDocSeries>\r\n" +
				"              <ATCompTypes:IdentityDocDate>2010-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"              <ATCompTypes:CountryCode>100</ATCompTypes:CountryCode>\r\n" +
				"              <ATCompTypes:INNID>123456789012</ATCompTypes:INNID>\r\n" +
				"              <ATCompTypes:AddressLine>AААА</ATCompTypes:AddressLine>\r\n" +
				"              <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"              <ATCompTypes:Name>ПЕТР</ATCompTypes:Name>\r\n" +
				"              <ATCompTypes:Patronymicname>СИДОРОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"            </Owner>\r\n" +
				"            <Driver>\r\n" +
				"              <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"              <ATCompTypes:OrganizationName>OrganizationName</ATCompTypes:OrganizationName>\r\n" +
				"              <ATCompTypes:IdentityDocCode>10</ATCompTypes:IdentityDocCode>\r\n" +
				"              <ATCompTypes:IdentityDocNumber>123456</ATCompTypes:IdentityDocNumber>\r\n" +
				"              <ATCompTypes:IdentityDocSeries>1231</ATCompTypes:IdentityDocSeries>\r\n" +
				"              <ATCompTypes:IdentityDocDate>2010-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"              <ATCompTypes:IdentityDocGiver>IdentityDocGiver</ATCompTypes:IdentityDocGiver>\r\n" +
				"              <ATCompTypes:CountryCode>100</ATCompTypes:CountryCode>\r\n" +
				"              <ATCompTypes:INNID>123456789012</ATCompTypes:INNID>\r\n" +
				"              <ATCompTypes:AddressLine>AААА</ATCompTypes:AddressLine>\r\n" +
				"              <ATCompTypes:Z_address>Z_address</ATCompTypes:Z_address>\r\n" +
				"              <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"              <ATCompTypes:Name>ПЕТР</ATCompTypes:Name>\r\n" +
				"              <ATCompTypes:Patronymicname>СИДОРОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"            </Driver>\r\n" +
				"          </Vehicle>\r\n" +
				"        </LinkedVeh>\r\n" +
				"      </AttachedVehicles>\r\n" +
				"      <StartInspectorLNP>\r\n" +
				"        <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"        <ATCompTypes:CustomsCode>10209000</ATCompTypes:CustomsCode>\r\n" +
				"      </StartInspectorLNP>\r\n" +
				"      <InspectorLNP>\r\n" +
				"        <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"        <ATCompTypes:CustomsCode>10209000</ATCompTypes:CustomsCode>\r\n" +
				"      </InspectorLNP>\r\n" +
				"      <CustomsNote>\r\n" +
				"        <ATCompTypes:CustomsNoteCodes>10200000</ATCompTypes:CustomsNoteCodes>\r\n" +
				"        <ATCompTypes:Description>НЕ УСТАНОВЛЕНЫ</ATCompTypes:Description>\r\n" +
				"      </CustomsNote>\r\n" +
				"    </TTinf>\r\n" +
				"  </Body>\r\n" +
				"</Envelope>";
		this.modifyXML= generateData(Cnfrm);
		return this.modifyXML;
	}

	public String transformateXML2() {
		String ED_Container = "";
		return generateData(ED_Container);
	}

	public String transformateXML3() {
		String Result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\" xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"  <Header>\r\n" +
				"    <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\">\r\n" +
				"      <EnvelopeID>b0744f43-c17f-483d-bb66-521ata401788</EnvelopeID>\r\n" +
				"<SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</SenderInformation>\r\n" +
				"<ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</ReceiverInformation>\r\n" +
				"      <PreparationDateTime>2017-03-07T15:21:19</PreparationDateTime>\r\n" +
				"    </RoutingInf>\r\n" +
				"    <ApplicationInf xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\">\r\n" +
				"      <MessageKind>I305</MessageKind>\r\n" +
				"    </ApplicationInf>\r\n" +
				"    <ATCompTypes:ATHeader>\r\n" +
				"      <ATCompTypes:EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"      <ATCompTypes:ToCustomsCode>10000000</ATCompTypes:ToCustomsCode>\r\n" +
				"      <ATCompTypes:ToCustomsSystem>0</ATCompTypes:ToCustomsSystem>\r\n" +
				"      <ATCompTypes:FromCustomsCode>10209000</ATCompTypes:FromCustomsCode>\r\n" +
				"      <ATCompTypes:FromCustomsSystem>16</ATCompTypes:FromCustomsSystem>\r\n" +
				"    </ATCompTypes:ATHeader>\r\n" +
				"  </Header>\r\n" +
				"  <Body>\r\n" +
				"    <TTinf xmlns=\"urn:customs.ru:Information:AutoTransport:ATttTypes:10.0\"\r\n" +
				"           xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\"\r\n" +
				"           xmlns:ATSimpTypes=\"urn:customs.ru:Information:AutoTransport:ATSimpTypes:10.0\"\r\n" +
				"           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"      <CustomsSystemType>16</CustomsSystemType>\r\n" +
				"      <Type>305</Type>\r\n" +
				"      <Number>10209001/070311/00000741</Number>\r\n" +
				"      <CustomsCode>10209000</CustomsCode>\r\n" +
				"      <CreateDateTime>2018-05-07T09:35:50.0000000+03:00</CreateDateTime>\r\n" +
				"      <DeliveryType>2</DeliveryType>\r\n" +
				"      <DeliveryDate>2018-05-22T00:00:00.0000000+03:00</DeliveryDate>\r\n" +
				"      <DeliveryFactCustomsCode>10200000</DeliveryFactCustomsCode>\r\n" +
				"      <DeliveryFactDate>2012-05-22T00:00:00.0000000+03:00</DeliveryFactDate>\r\n" +
				"      <BlankNumber>00000745</BlankNumber>\r\n" +
				"      <BlankIssuedDate>2017-03-07T00:00:00.0000000+03:00</BlankIssuedDate>\r\n" +
				"      <DeliveryCustomsCode>10207000</DeliveryCustomsCode>\r\n" +
				"      <StartDate>2017-03-07T00:00:00.0000000+03:00</StartDate>\r\n" +
				"      <StartCustomsCode>10209000</StartCustomsCode>\r\n" +
				"      <Comment>Comment</Comment>\r\n" +
				"      <VersionDateTime>2017-03-07T09:36:09.0000000+03:00</VersionDateTime>\r\n" +
				"      <DocSendDate>2017-03-07T09:35:50.0000000+03:00</DocSendDate>\r\n" +
				"      <ApplicationName>ASKTT2</ApplicationName>\r\n" +
				"      <ApplicactionVersion>2.11</ApplicactionVersion>\r\n" +
				"      <AttachedVehicles>\r\n" +
				"        <LinkedVeh>\r\n" +
				"          <VehicleRole>4</VehicleRole>\r\n" +
				"          <VehicleOrderNumber>1</VehicleOrderNumber>\r\n" +
				"          <PrevGoodsItem>\r\n" +
				"            <ATCompTypes:PrevDocNumber>PrevDocNumber</ATCompTypes:PrevDocNumber>\r\n" +
				"            <ATCompTypes:PrevDocType>312</ATCompTypes:PrevDocType>\r\n" +
				"            <ATCompTypes:PrevGoodsNumber>123</ATCompTypes:PrevGoodsNumber>\r\n" +
				"          </PrevGoodsItem>\r\n" +
				"          <GoodsItem>\r\n" +
				"            <ATCompTypes:GoodsName>GoodsName</ATCompTypes:GoodsName>\r\n" +
				"            <ATCompTypes:GoodsNumber>123</ATCompTypes:GoodsNumber>\r\n" +
				"          </GoodsItem>\r\n" +
				"          <Vehicle>\r\n" +
				"            <StateSignID>ata6555931K171000</StateSignID>\r\n" +
				"            <VINID>ata6555931K171000</VINID>\r\n" +
				"            <TNVEDCode>8703231910</TNVEDCode>\r\n" +
				"            <Type>20</Type>\r\n" +
				"            <MarkCode>79</MarkCode>\r\n" +
				"            <Model>XX</Model>\r\n" +
				"            <OfftakeYear>2008</OfftakeYear>\r\n" +
				"            <EngineID>0987631</EngineID>\r\n" +
				"            <ChassisID>ata6555931K171000</ChassisID>\r\n" +
				"            <BodyID>ata6555931K171000</BodyID>\r\n" +
				"            <CabinID>ata6555931K171000</CabinID>\r\n" +
				"            <NationalityCode>100</NationalityCode>\r\n" +
				"            <FromCountryCode>860</FromCountryCode>\r\n" +
				"            <OwnDocNames>OwnDocNames</OwnDocNames>\r\n" +
				"            <IdentDocNames>IdentDocNames</IdentDocNames>\r\n" +
				"            <FStoim>11111</FStoim>\r\n" +
				"            <FSValKod>RUB</FSValKod>\r\n" +
				"            <Owner>\r\n" +
				"              <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"              <ATCompTypes:OrganizationName>OrganizationName</ATCompTypes:OrganizationName>\r\n" +
				"              <ATCompTypes:IdentityDocCode>10</ATCompTypes:IdentityDocCode>\r\n" +
				"              <ATCompTypes:IdentityDocNumber>123456</ATCompTypes:IdentityDocNumber>\r\n" +
				"              <ATCompTypes:IdentityDocSeries>1231</ATCompTypes:IdentityDocSeries>\r\n" +
				"              <ATCompTypes:IdentityDocDate>2010-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"              <ATCompTypes:CountryCode>100</ATCompTypes:CountryCode>\r\n" +
				"              <ATCompTypes:INNID>123456789012</ATCompTypes:INNID>\r\n" +
				"              <ATCompTypes:AddressLine>AААА</ATCompTypes:AddressLine>\r\n" +
				"              <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"              <ATCompTypes:Name>ПЕТР</ATCompTypes:Name>\r\n" +
				"              <ATCompTypes:Patronymicname>СИДОРОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"            </Owner>\r\n" +
				"            <Driver>\r\n" +
				"              <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"              <ATCompTypes:OrganizationName>OrganizationName</ATCompTypes:OrganizationName>\r\n" +
				"              <ATCompTypes:IdentityDocCode>10</ATCompTypes:IdentityDocCode>\r\n" +
				"              <ATCompTypes:IdentityDocNumber>123456</ATCompTypes:IdentityDocNumber>\r\n" +
				"              <ATCompTypes:IdentityDocSeries>1231</ATCompTypes:IdentityDocSeries>\r\n" +
				"              <ATCompTypes:IdentityDocDate>2010-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"              <ATCompTypes:IdentityDocGiver>IdentityDocGiver</ATCompTypes:IdentityDocGiver>\r\n" +
				"              <ATCompTypes:CountryCode>100</ATCompTypes:CountryCode>\r\n" +
				"              <ATCompTypes:INNID>123456789012</ATCompTypes:INNID>\r\n" +
				"              <ATCompTypes:AddressLine>AААА</ATCompTypes:AddressLine>\r\n" +
				"              <ATCompTypes:Z_address>Z_address</ATCompTypes:Z_address>\r\n" +
				"              <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"              <ATCompTypes:Name>ПЕТР</ATCompTypes:Name>\r\n" +
				"              <ATCompTypes:Patronymicname>СИДОРОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"            </Driver>\r\n" +
				"          </Vehicle>\r\n" +
				"        </LinkedVeh>\r\n" +
				"      </AttachedVehicles>\r\n" +
				"      <StartInspectorLNP>\r\n" +
				"        <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"        <ATCompTypes:CustomsCode>10209000</ATCompTypes:CustomsCode>\r\n" +
				"      </StartInspectorLNP>\r\n" +
				"      <InspectorLNP>\r\n" +
				"        <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"        <ATCompTypes:CustomsCode>10209000</ATCompTypes:CustomsCode>\r\n" +
				"      </InspectorLNP>\r\n" +
				"      <CustomsNote>\r\n" +
				"        <ATCompTypes:CustomsNoteCodes>10200000</ATCompTypes:CustomsNoteCodes>\r\n" +
				"        <ATCompTypes:Description>НЕ УСТАНОВЛЕНЫ</ATCompTypes:Description>\r\n" +
				"      </CustomsNote>\r\n" +
				"    </TTinf>\r\n" +
				"  </Body>\r\n" +
				"</Envelope>";
		this.modifyXML= generateData(Result);
		return this.modifyXML;
	}

	public String transformateXML4() {
		String VVinq = "";
		return generateData(VVinq);

	}

	public String transformateXML5() {
		String VVdrvrInq = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\" xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				" <Header>\r\n" +
				"  <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\">\r\n" +
				"   <EnvelopeID>d7744f43-c17f-483d-bb66-521ata416055</EnvelopeID>\r\n" +
				"   <SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</SenderInformation>\r\n" +
				"   <ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</ReceiverInformation>\r\n" +
				"   <PreparationDateTime>2018-03-07T15:21:19</PreparationDateTime>\r\n" +
				"  </RoutingInf>\r\n" +
				"  <ApplicationInf xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\">\r\n" +
				"   <MessageKind>I416</MessageKind>\r\n" +
				"  </ApplicationInf>\r\n" +
				"  <ATCompTypes:ATHeader>\r\n" +
				"   <ATCompTypes:EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"   <ATCompTypes:ToCustomsCode>10000000</ATCompTypes:ToCustomsCode>\r\n" +
				"   <ATCompTypes:ToCustomsSystem>0</ATCompTypes:ToCustomsSystem>\r\n" +
				"   <ATCompTypes:FromCustomsCode>10101020</ATCompTypes:FromCustomsCode>\r\n" +
				"   <ATCompTypes:FromCustomsSystem>00</ATCompTypes:FromCustomsSystem>\r\n" +
				"  </ATCompTypes:ATHeader>\r\n" +
				" </Header>\r\n" +
				" <Body>\r\n" +
				"  <VVinf xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ATSimpTypes=\"urn:customs.ru:Information:AutoTransport:ATSimpTypes:10.0\">\r\n" +
				"   <CustomsSystemType>00</CustomsSystemType>\r\n" +
				"   <Type>416</Type>\r\n" +
				"   <Number>10101020/070312/В000006</Number>\r\n" +
				"   <CustomsCode>10101020</CustomsCode>\r\n" +
				"   <CreateDateTime>2018-03-07T09:35:50.0000000+03:00</CreateDateTime>\r\n" +
				"   <DeliveryType>2</DeliveryType>\r\n" +
				"   <DeliveryDate>2017-05-12T00:00:00.0000000+03:00</DeliveryDate>\r\n" +
				"   <BlankNumber>10101020/070312/В000005</BlankNumber>\r\n" +
				"   <StartDate>2018-03-07T00:00:00.0000000+03:00</StartDate>\r\n" +
				"   <StartCustomsCode>10101020</StartCustomsCode>\r\n" +
				"   <VersionDateTime>2017-09-07T09:36:08.0000000+03:00</VersionDateTime>\r\n" +
				"   <DocSendDate>2018-03-07T09:35:50</DocSendDate>\r\n" +
				"   <ApplicationName>AS PP</ApplicationName>\r\n" +
				"   <ApplicactionVersion>12.01</ApplicactionVersion>\r\n" +
				"   <AttachedVehicles>\r\n" +
				"    <LinkedVeh>\r\n" +
				"     <VehicleRole>1</VehicleRole>\r\n" +
				"     <Vehicle>\r\n" +
				"      <StateSignID>ata005</StateSignID>\r\n" +
				"      <VINID>ata6555931K171005</VINID>\r\n" +
				"      <Type>20</Type>\r\n" +
				"      <Model>XX</Model>\r\n" +
				"      <NationalityCode>100</NationalityCode>\r\n" +
				"      <Owner>\r\n" +
				"       <ATCompTypes:Type>В</ATCompTypes:Type>\r\n" +
				"       <ATCompTypes:IdentityDocCode>11</ATCompTypes:IdentityDocCode>\r\n" +
				"       <ATCompTypes:IdentityDocNumber>223456</ATCompTypes:IdentityDocNumber>\r\n" +
				"       <ATCompTypes:IdentityDocDate>2010-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"       <ATCompTypes:CountryCode>643</ATCompTypes:CountryCode>\r\n" +
				"       <ATCompTypes:AddressLine>АААА</ATCompTypes:AddressLine>\r\n" +
				"       <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"       <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"       <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"      </Owner>\r\n" +
				"       <Driver>\r\n" +
				"         <ATCompTypes:Type>ФФ</ATCompTypes:Type>\r\n" +
				"         <ATCompTypes:IdentityDocCode>11</ATCompTypes:IdentityDocCode>\r\n" +
				"         <ATCompTypes:IdentityDocNumber>223456</ATCompTypes:IdentityDocNumber>\r\n" +
				"         <ATCompTypes:IdentityDocSeries>1231</ATCompTypes:IdentityDocSeries>\r\n" +
				"         <ATCompTypes:IdentityDocDate>2012-12-20T00:00:00.0000000+03:00</ATCompTypes:IdentityDocDate>\r\n" +
				"         <ATCompTypes:CountryCode>100</ATCompTypes:CountryCode>\r\n" +
				"         <ATCompTypes:AddressLine>AАААА</ATCompTypes:AddressLine>\r\n" +
				"         <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"         <ATCompTypes:Name>ПЕТР</ATCompTypes:Name>\r\n" +
				"         <ATCompTypes:Patronymicname>СИДОРОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"         <ATCompTypes:Address>127055</ATCompTypes:Address>\r\n" +
				"         <ATCompTypes:Address>7</ATCompTypes:Address>\r\n" +
				"         <ATCompTypes:Address>Москва</ATCompTypes:Address>\r\n" +
				"         <ATCompTypes:Address>Москва</ATCompTypes:Address>\r\n" +
				"         <ATCompTypes:Address>Проспект</ATCompTypes:Address>\r\n" +
				"         <ATCompTypes:BirthPlace>Москва</ATCompTypes:BirthPlace>\r\n" +
				"         <ATCompTypes:NationalityCountryCode>643</ATCompTypes:NationalityCountryCode>\r\n" +
				"      </Driver>\r\n" +
				"     </Vehicle>\r\n" +
				"    </LinkedVeh>\r\n" +
				"   </AttachedVehicles>\r\n" +
				"   <StartInspectorLNP>\r\n" +
				"    <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"    <ATCompTypes:CustomsCode>10101020</ATCompTypes:CustomsCode>\r\n" +
				"   </StartInspectorLNP>\r\n" +
				"   <InspectorLNP>\r\n" +
				"    <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"    <ATCompTypes:CustomsCode>10101020</ATCompTypes:CustomsCode>\r\n" +
				"   </InspectorLNP>\r\n" +
				"  </VVinf>\r\n" +
				" </Body>\r\n" +
				"</Envelope>";

		this.modifyXML= generateData(VVdrvrInq);
		return this.modifyXML;

	}

	public String transformateXML6() {
		String VVinfAlone = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns=\"http://www.w3.org/2001/06/soap-envelope\">\r\n" +
				" <Header>\r\n" +
				"  <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\">\r\n" +
				"   <EnvelopeID>b4444f43-c17f-483d-bb66-521RBA422202</EnvelopeID>\r\n" +
				"   <SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</SenderInformation>\r\n" +
				"   <ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</ReceiverInformation>\r\n" +
				"   <PreparationDateTime>2018-03-01T15:21:19</PreparationDateTime>\r\n" +
				"  </RoutingInf>\r\n" +
				"  <ApplicationInf xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\">\r\n" +
				"   <MessageKind>I422</MessageKind>\r\n" +
				"  </ApplicationInf>\r\n" +
				"  <ATCompTypes:ATHeader>\r\n" +
				"   <ATCompTypes:EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"   <ATCompTypes:ToCustomsCode>10000000</ATCompTypes:ToCustomsCode>\r\n" +
				"   <ATCompTypes:ToCustomsSystem>0</ATCompTypes:ToCustomsSystem>\r\n" +
				"   <ATCompTypes:FromCustomsCode>10206000</ATCompTypes:FromCustomsCode>\r\n" +
				"   <ATCompTypes:FromCustomsSystem>19</ATCompTypes:FromCustomsSystem>\r\n" +
				"  </ATCompTypes:ATHeader>\r\n" +
				" </Header>\r\n" +
				" <Body>\r\n" +
				"  <VVinf xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ATSimpTypes=\"urn:customs.ru:Information:AutoTransport:ATSimpTypes:10.0\">\r\n" +
				"   <CustomsSystemType>19</CustomsSystemType>\r\n" +
				"   <Type>422</Type>\r\n" +
				"   <Number>10206000/010313/В00002</Number>\r\n" +
				"   <CustomsCode>10206000</CustomsCode>\r\n" +
				"   <CreateDateTime>2018-03-01T00:00:00.000+03:00</CreateDateTime>\r\n" +
				"   <DeliveryType>2</DeliveryType>\r\n" +
				"   <DeliveryFactCustomsCode>10206000</DeliveryFactCustomsCode>\r\n" +
				"   <DeliveryFactDate>2018-03-01T14:38:27.000+03:00</DeliveryFactDate>\r\n" +
				"   <BlankNumber>11209104/010113/300000002</BlankNumber>\r\n" +
				"   <StartDate>2018-01-01T14:38:27.000+03:00</StartDate>\r\n" +
				"   <StartCustomsCode>11209104</StartCustomsCode>\r\n" +
				"   <VersionDateTime>2018-03-01T19:20:45.000+03:00</VersionDateTime>\r\n" +
				"   <DocSendDate>2018-03-01T19:20:34.000+03:00</DocSendDate>\r\n" +
				"   <ApplicationName>AS PP</ApplicationName>\r\n" +
				"   <ApplicactionVersion>12.0</ApplicactionVersion>\r\n" +
				"   <AttachedVehicles>\r\n" +
				"    <LinkedVeh>\r\n" +
				"     <VehicleRole>1</VehicleRole>\r\n" +
				"     <Vehicle>\r\n" +
				"      <StateSignID>RB000002</StateSignID>\r\n" +
				"      <VINID>RBA3K31CD8A000002</VINID>\r\n" +
				"      <Type>01</Type>\r\n" +
				"      <Model>XX</Model>\r\n" +
				"     </Vehicle>\r\n" +
				"    </LinkedVeh>\r\n" +
				"   </AttachedVehicles>\r\n" +
				"   <StartInspectorLNP>\r\n" +
				"    <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"    <ATCompTypes:CustomsCode>10206000</ATCompTypes:CustomsCode>\r\n" +
				"   </StartInspectorLNP>\r\n" +
				"   <InspectorLNP>\r\n" +
				"    <ATCompTypes:Number>001</ATCompTypes:Number>\r\n" +
				"    <ATCompTypes:CustomsCode>10206000</ATCompTypes:CustomsCode>\r\n" +
				"   </InspectorLNP>\r\n" +
				"  </VVinf>\r\n" +
				" </Body>\r\n" +
				"</Envelope>";

		this.modifyXML= generateData(VVinfAlone);
		return this.modifyXML;

	}

	public String transformateXML7() {
		String FOIVRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\"\r\n" +
				"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"    <Header>\r\n" +
				"        <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <EnvelopeID>223f04f3-f5ce-46e4-b708-577d1df561af</EnvelopeID>\r\n" +
				"            <SenderInformation>wmq://RU.FTS.GNIVC.SAND/KPSPP.INCOME</SenderInformation>\r\n" +
				"            <ReceiverInformation>wmq://RU.FTS.GNIVC.SAND/AT.INCOME</ReceiverInformation>\r\n" +
				"            <PreparationDateTime>2018-06-20T08:59:54</PreparationDateTime>\r\n" +
				"            <Priority>4</Priority>\r\n" +
				"        </RoutingInf>\r\n" +
				"        <ApplicationInf\r\n" +
				"            xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <SoftVersion>5.0.5/3.0.0</SoftVersion>\r\n" +
				"            <MessageKind>I424</MessageKind>\r\n" +
				"        </ApplicationInf>\r\n" +
				"        <ATHeader xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"            <EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"            <ToCustomsCode>10000000</ToCustomsCode>\r\n" +
				"            <ToCustomsSystem>0</ToCustomsSystem>\r\n" +
				"            <FromCustomsCode>10115010</FromCustomsCode>\r\n" +
				"            <FromCustomsSystem>19</FromCustomsSystem>\r\n" +
				"        </ATHeader>\r\n" +
				"    </Header>\r\n" +
				"    <Body>\r\n" +
				"        <ATvvTypes:VVinf\r\n" +
				"            xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns:ATvvTypes=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\">\r\n" +
				"            <ATvvTypes:OperationID>797d593d-f2f0-411c-87bd-51a501b81b21</ATvvTypes:OperationID>\r\n" +
				"            <ATvvTypes:CustomsSystemType>19</ATvvTypes:CustomsSystemType>\r\n" +
				"            <ATvvTypes:Type>424</ATvvTypes:Type>\r\n" +
				"            <ATvvTypes:Number>10115010/200618/Я0000214</ATvvTypes:Number>\r\n" +
				"            <ATvvTypes:CustomsCode>10115010</ATvvTypes:CustomsCode>\r\n" +
				"            <ATvvTypes:CreateDateTime>2018-06-20T12:59:51+04:00</ATvvTypes:CreateDateTime>\r\n" +
				"            <ATvvTypes:DeliveryType>2</ATvvTypes:DeliveryType>\r\n" +
				"            <ATvvTypes:DeliveryDate>2019-06-20T12:59:17+04:00</ATvvTypes:DeliveryDate>\r\n" +
				"            <ATvvTypes:BlankNumber>10115010/200618/В0000210</ATvvTypes:BlankNumber>\r\n" +
				"            <ATvvTypes:StartDate>2018-06-20T12:23:05+04:00</ATvvTypes:StartDate>\r\n" +
				"            <ATvvTypes:StartCustomsCode>10115010</ATvvTypes:StartCustomsCode>\r\n" +
				"            <ATvvTypes:VersionDateTime>2018-06-20T12:59:51+04:00</ATvvTypes:VersionDateTime>\r\n" +
				"            <ATvvTypes:DocSendDate>2018-06-20T12:59:51+04:00</ATvvTypes:DocSendDate>\r\n" +
				"            <ATvvTypes:ApplicationName>АС \"ПП\"</ATvvTypes:ApplicationName>\r\n" +
				"            <ATvvTypes:ApplicactionVersion>11.0.0.9786</ATvvTypes:ApplicactionVersion>\r\n" +
				"            <ATvvTypes:AttachedVehicles>\r\n" +
				"                <ATvvTypes:LinkedVeh>\r\n" +
				"                    <ATvvTypes:VehicleRole>1</ATvvTypes:VehicleRole>\r\n" +
				"                    <ATvvTypes:Vehicle>\r\n" +
				"                        <ATvvTypes:StateSignID>RT96789</ATvvTypes:StateSignID>\r\n" +
				"                        <ATvvTypes:VINID>GGFD768776FCGD</ATvvTypes:VINID>\r\n" +
				"                        <ATvvTypes:Type>20</ATvvTypes:Type>\r\n" +
				"                        <ATvvTypes:MarkCode>007</ATvvTypes:MarkCode>\r\n" +
				"                        <ATvvTypes:Model>t500</ATvvTypes:Model>\r\n" +
				"                        <ATvvTypes:CategoryCode>B</ATvvTypes:CategoryCode>\r\n" +
				"                        <ATvvTypes:OfftakeYear>2009</ATvvTypes:OfftakeYear>\r\n" +
				"                        <ATvvTypes:EngineVolume>2000</ATvvTypes:EngineVolume>\r\n" +
				"                        <ATvvTypes:NationalityCode>AB</ATvvTypes:NationalityCode>\r\n" +
				"                        <ATvvTypes:RunningPeriod>8</ATvvTypes:RunningPeriod>\r\n" +
				"                        <ATvvTypes:OperationStatus>0</ATvvTypes:OperationStatus>\r\n" +
				"                        <ATvvTypes:OperationStatusNote>Операция открытия временного ввоза транспортного средства, срок временного ввоза не нарушен</ATvvTypes:OperationStatusNote>\r\n" +
				"                        <ATvvTypes:Owner>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Z_address>Москва, 16-я Парковая</ATCompTypes:Z_address>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Owner>\r\n" +
				"                        <ATvvTypes:Driver>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Driver>\r\n" +
				"                    </ATvvTypes:Vehicle>\r\n" +
				"                </ATvvTypes:LinkedVeh>\r\n" +
				"            </ATvvTypes:AttachedVehicles>\r\n" +
				"            <ATvvTypes:StartInspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"            </ATvvTypes:StartInspectorLNP>\r\n" +
				"            <ATvvTypes:InspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"                <ATCompTypes:Name>Test Test</ATCompTypes:Name>\r\n" +
				"            </ATvvTypes:InspectorLNP>\r\n" +
				"            <ATvvTypes:CustomsNote/>\r\n" +
				"        </ATvvTypes:VVinf>\r\n" +
				"    </Body>\r\n" +
				"</Envelope>\r\n";


		this.modifyXML= generateData(FOIVRequest);
		return this.modifyXML;
	}

	public String transformateXML8() {
		String Notif_PIResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\"\r\n" +
				"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"    <Header>\r\n" +
				"        <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <EnvelopeID>b95f6eef-1579-462c-bd4d-63cc6c4d79d3</EnvelopeID>\r\n" +
				"            <SenderInformation>wmq://RU.FTS.GNIVC.SAND/KPSPP.INCOME</SenderInformation>\r\n" +
				"            <ReceiverInformation>wmq://RU.FTS.GNIVC.SAND/AT.INCOME</ReceiverInformation>\r\n" +
				"            <PreparationDateTime>2018-06-20T09:03:54</PreparationDateTime>\r\n" +
				"            <Priority>4</Priority>\r\n" +
				"        </RoutingInf>\r\n" +
				"        <ApplicationInf\r\n" +
				"            xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <SoftVersion>5.0.5/3.0.0</SoftVersion>\r\n" +
				"            <MessageKind>I425</MessageKind>\r\n" +
				"        </ApplicationInf>\r\n" +
				"        <ATHeader xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"            <EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"            <ToCustomsCode>10000000</ToCustomsCode>\r\n" +
				"            <ToCustomsSystem>0</ToCustomsSystem>\r\n" +
				"            <FromCustomsCode>10115010</FromCustomsCode>\r\n" +
				"            <FromCustomsSystem>19</FromCustomsSystem>\r\n" +
				"        </ATHeader>\r\n" +
				"    </Header>\r\n" +
				"    <Body>\r\n" +
				"        <ATvvTypes:VVinf\r\n" +
				"            xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns:ATvvTypes=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\">\r\n" +
				"            <ATvvTypes:OperationID>4fe4ab44-cf30-49ee-9aa0-b612b48d2ee6</ATvvTypes:OperationID>\r\n" +
				"            <ATvvTypes:CustomsSystemType>19</ATvvTypes:CustomsSystemType>\r\n" +
				"            <ATvvTypes:Type>425</ATvvTypes:Type>\r\n" +
				"            <ATvvTypes:Number>10115010/200618/Ю0000215</ATvvTypes:Number>\r\n" +
				"            <ATvvTypes:CustomsCode>10115010</ATvvTypes:CustomsCode>\r\n" +
				"            <ATvvTypes:CreateDateTime>2018-06-20T13:03:50+04:00</ATvvTypes:CreateDateTime>\r\n" +
				"            <ATvvTypes:DeliveryType>2</ATvvTypes:DeliveryType>\r\n" +
				"            <ATvvTypes:DeliveryDate>2019-06-20T12:20:16+04:00</ATvvTypes:DeliveryDate>\r\n" +
				"            <ATvvTypes:DeliveryFactCustomsCode>10115010</ATvvTypes:DeliveryFactCustomsCode>\r\n" +
				"            <ATvvTypes:DeliveryFactDate>2018-06-20T13:01:28+04:00</ATvvTypes:DeliveryFactDate>\r\n" +
				"            <ATvvTypes:BlankNumber>10115010/200618/В0000210</ATvvTypes:BlankNumber>\r\n" +
				"            <ATvvTypes:StartDate>2018-06-20T12:23:05+04:00</ATvvTypes:StartDate>\r\n" +
				"            <ATvvTypes:StartCustomsCode>10115010</ATvvTypes:StartCustomsCode>\r\n" +
				"            <ATvvTypes:VersionDateTime>2018-06-20T13:03:51+04:00</ATvvTypes:VersionDateTime>\r\n" +
				"            <ATvvTypes:DocSendDate>2018-06-20T13:03:51+04:00</ATvvTypes:DocSendDate>\r\n" +
				"            <ATvvTypes:ApplicationName>АС \"ПП\"</ATvvTypes:ApplicationName>\r\n" +
				"            <ATvvTypes:ApplicactionVersion>11.0.0.9786</ATvvTypes:ApplicactionVersion>\r\n" +
				"            <ATvvTypes:AttachedVehicles>\r\n" +
				"                <ATvvTypes:LinkedVeh>\r\n" +
				"                    <ATvvTypes:VehicleRole>1</ATvvTypes:VehicleRole>\r\n" +
				"                    <ATvvTypes:Vehicle>\r\n" +
				"                        <ATvvTypes:StateSignID>RT96789</ATvvTypes:StateSignID>\r\n" +
				"                        <ATvvTypes:VINID>GGFD768776FCGD</ATvvTypes:VINID>\r\n" +
				"                        <ATvvTypes:Type>20</ATvvTypes:Type>\r\n" +
				"                        <ATvvTypes:MarkCode>007</ATvvTypes:MarkCode>\r\n" +
				"                        <ATvvTypes:Model>t500</ATvvTypes:Model>\r\n" +
				"                        <ATvvTypes:CategoryCode>B</ATvvTypes:CategoryCode>\r\n" +
				"                        <ATvvTypes:OfftakeYear>2009</ATvvTypes:OfftakeYear>\r\n" +
				"                        <ATvvTypes:EngineVolume>2000</ATvvTypes:EngineVolume>\r\n" +
				"                        <ATvvTypes:NationalityCode>AB</ATvvTypes:NationalityCode>\r\n" +
				"                        <ATvvTypes:RunningPeriod>8</ATvvTypes:RunningPeriod>\r\n" +
				"                        <ATvvTypes:OperationStatus>2</ATvvTypes:OperationStatus>\r\n" +
				"                        <ATvvTypes:OperationStatusNote>Операция закрытия временного ввоза (вывоз) транспортного средства, срок временного ввоза не нарушен</ATvvTypes:OperationStatusNote>\r\n" +
				"                        <ATvvTypes:Owner>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Z_address>Москва, 16-я Парковая</ATCompTypes:Z_address>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Owner>\r\n" +
				"                        <ATvvTypes:Driver>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Driver>\r\n" +
				"                    </ATvvTypes:Vehicle>\r\n" +
				"                </ATvvTypes:LinkedVeh>\r\n" +
				"            </ATvvTypes:AttachedVehicles>\r\n" +
				"            <ATvvTypes:StartInspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"            </ATvvTypes:StartInspectorLNP>\r\n" +
				"            <ATvvTypes:InspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"                <ATCompTypes:Name>Test Test</ATCompTypes:Name>\r\n" +
				"            </ATvvTypes:InspectorLNP>\r\n" +
				"            <ATvvTypes:CustomsNote/>\r\n" +
				"        </ATvvTypes:VVinf>\r\n" +
				"    </Body>\r\n" +
				"</Envelope>\r\n";

		this.modifyXML= generateData(Notif_PIResult);
		return this.modifyXML;
	}

	public String transformateXML9() {
		String LotArrival = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\"\r\n" +
				"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"    <Header>\r\n" +
				"        <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <EnvelopeID>56c0f4ea-beee-453c-a850-c54c37ec2a9c</EnvelopeID>\r\n" +
				"            <SenderInformation>wmq://RU.FTS.GNIVC.SAND/KPSPP.INCOME</SenderInformation>\r\n" +
				"            <ReceiverInformation>wmq://RU.FTS.GNIVC.SAND/AT.INCOME</ReceiverInformation>\r\n" +
				"            <PreparationDateTime>2018-06-20T08:24:16</PreparationDateTime>\r\n" +
				"            <Priority>4</Priority>\r\n" +
				"        </RoutingInf>\r\n" +
				"        <ApplicationInf\r\n" +
				"            xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <SoftVersion>5.0.5/3.0.0</SoftVersion>\r\n" +
				"            <MessageKind>I418</MessageKind>\r\n" +
				"        </ApplicationInf>\r\n" +
				"        <ATHeader xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"            <EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"            <ToCustomsCode>10000000</ToCustomsCode>\r\n" +
				"            <ToCustomsSystem>0</ToCustomsSystem>\r\n" +
				"            <FromCustomsCode>10115010</FromCustomsCode>\r\n" +
				"            <FromCustomsSystem>19</FromCustomsSystem>\r\n" +
				"        </ATHeader>\r\n" +
				"    </Header>\r\n" +
				"    <Body>\r\n" +
				"        <ATvvTypes:VVinf\r\n" +
				"            xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns:ATvvTypes=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\">\r\n" +
				"            <ATvvTypes:OperationID>037c9322-14a4-48cf-aaeb-7918f78b08ed</ATvvTypes:OperationID>\r\n" +
				"            <ATvvTypes:CustomsSystemType>19</ATvvTypes:CustomsSystemType>\r\n" +
				"            <ATvvTypes:Type>418</ATvvTypes:Type>\r\n" +
				"            <ATvvTypes:Number>10115010/200618/Э0000211</ATvvTypes:Number>\r\n" +
				"            <ATvvTypes:CustomsCode>10115010</ATvvTypes:CustomsCode>\r\n" +
				"            <ATvvTypes:CreateDateTime>2018-06-20T12:24:11+04:00</ATvvTypes:CreateDateTime>\r\n" +
				"            <ATvvTypes:DeliveryType>2</ATvvTypes:DeliveryType>\r\n" +
				"            <ATvvTypes:DeliveryDate>2019-06-20T12:20:16+04:00</ATvvTypes:DeliveryDate>\r\n" +
				"            <ATvvTypes:DeliveryFactCustomsCode>10115010</ATvvTypes:DeliveryFactCustomsCode>\r\n" +
				"            <ATvvTypes:DeliveryFactDate>2018-06-20T12:24:12+04:00</ATvvTypes:DeliveryFactDate>\r\n" +
				"            <ATvvTypes:BlankNumber>10115010/200618/В0000210</ATvvTypes:BlankNumber>\r\n" +
				"            <ATvvTypes:StartDate>2018-06-20T12:23:05+04:00</ATvvTypes:StartDate>\r\n" +
				"            <ATvvTypes:StartCustomsCode>10115010</ATvvTypes:StartCustomsCode>\r\n" +
				"            <ATvvTypes:VersionDateTime>2018-06-20T12:24:12+04:00</ATvvTypes:VersionDateTime>\r\n" +
				"            <ATvvTypes:DocSendDate>2018-06-20T12:24:12+04:00</ATvvTypes:DocSendDate>\r\n" +
				"            <ATvvTypes:ApplicationName>АС \"ПП\"</ATvvTypes:ApplicationName>\r\n" +
				"            <ATvvTypes:ApplicactionVersion>11.0.0.9786</ATvvTypes:ApplicactionVersion>\r\n" +
				"            <ATvvTypes:AttachedVehicles>\r\n" +
				"                <ATvvTypes:LinkedVeh>\r\n" +
				"                    <ATvvTypes:VehicleRole>1</ATvvTypes:VehicleRole>\r\n" +
				"                    <ATvvTypes:Vehicle>\r\n" +
				"                        <ATvvTypes:StateSignID>RT96789</ATvvTypes:StateSignID>\r\n" +
				"                        <ATvvTypes:VINID>GGFD768776FCGD</ATvvTypes:VINID>\r\n" +
				"                        <ATvvTypes:Type>20</ATvvTypes:Type>\r\n" +
				"                        <ATvvTypes:MarkCode>007</ATvvTypes:MarkCode>\r\n" +
				"                        <ATvvTypes:Model>t500</ATvvTypes:Model>\r\n" +
				"                        <ATvvTypes:CategoryCode>B</ATvvTypes:CategoryCode>\r\n" +
				"                        <ATvvTypes:OfftakeYear>2009</ATvvTypes:OfftakeYear>\r\n" +
				"                        <ATvvTypes:EngineVolume>2000</ATvvTypes:EngineVolume>\r\n" +
				"                        <ATvvTypes:NationalityCode>AB</ATvvTypes:NationalityCode>\r\n" +
				"                        <ATvvTypes:RunningPeriod>8</ATvvTypes:RunningPeriod>\r\n" +
				"                        <ATvvTypes:OperationStatus>2</ATvvTypes:OperationStatus>\r\n" +
				"                        <ATvvTypes:OperationStatusNote>Операция закрытия временного ввоза (вывоз) транспортного средства, срок временного ввоза не нарушен</ATvvTypes:OperationStatusNote>\r\n" +
				"                        <ATvvTypes:Owner>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Z_address>Москва, 16-я Парковая</ATCompTypes:Z_address>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Owner>\r\n" +
				"                        <ATvvTypes:Driver>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                        </ATvvTypes:Driver>\r\n" +
				"                    </ATvvTypes:Vehicle>\r\n" +
				"                </ATvvTypes:LinkedVeh>\r\n" +
				"            </ATvvTypes:AttachedVehicles>\r\n" +
				"            <ATvvTypes:StartInspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"            </ATvvTypes:StartInspectorLNP>\r\n" +
				"            <ATvvTypes:InspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"                <ATCompTypes:Name>Test Test</ATCompTypes:Name>\r\n" +
				"            </ATvvTypes:InspectorLNP>\r\n" +
				"            <ATvvTypes:CustomsNote/>\r\n" +
				"        </ATvvTypes:VVinf>\r\n" +
				"    </Body>\r\n" +
				"</Envelope>";

		this.modifyXML= generateData(LotArrival);
		return this.modifyXML;
	}

	public String transformateXML10() {
		String Vvinf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<Envelope xmlns=\"http://www.w3.org/2001/06/soap-envelope\"\r\n" +
				"    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"    <Header>\r\n" +
				"        <RoutingInf xmlns=\"urn:customs.ru:Envelope:RoutingInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <EnvelopeID>d396bd87-534d-4bac-8dba-d2e7b0c106b6</EnvelopeID>\r\n" +
				"            <SenderInformation>wmq://RU.FTS.GNIVC.SAND/KPSPP.INCOME</SenderInformation>\r\n" +
				"            <ReceiverInformation>wmq://RU.FTS.GNIVC.SAND/AT.INCOME</ReceiverInformation>\r\n" +
				"            <PreparationDateTime>2018-06-20T08:55:06</PreparationDateTime>\r\n" +
				"            <Priority>4</Priority>\r\n" +
				"        </RoutingInf>\r\n" +
				"        <ApplicationInf\r\n" +
				"            xmlns=\"urn:customs.ru:Envelope:ApplicationInf:1.0\"\r\n" +
				"            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" +
				"            <SoftVersion>5.0.5/3.0.0</SoftVersion>\r\n" +
				"            <MessageKind>I420</MessageKind>\r\n" +
				"        </ApplicationInf>\r\n" +
				"        <ATHeader xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"            <EnvelopeInformationType Version=\"10.0\"/>\r\n" +
				"            <ToCustomsCode>10000000</ToCustomsCode>\r\n" +
				"            <ToCustomsSystem>0</ToCustomsSystem>\r\n" +
				"            <FromCustomsCode>10115010</FromCustomsCode>\r\n" +
				"            <FromCustomsSystem>19</FromCustomsSystem>\r\n" +
				"        </ATHeader>\r\n" +
				"    </Header>\r\n" +
				"    <Body>\r\n" +
				"        <ATvvTypes:VVinf\r\n" +
				"            xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\" xmlns:ATvvTypes=\"urn:customs.ru:Information:AutoTransport:ATvvTypes:10.0\">\r\n" +
				"            <ATvvTypes:OperationID>efd8e61a-24d1-4e42-b4e0-5cc1b837c06f</ATvvTypes:OperationID>\r\n" +
				"            <ATvvTypes:CustomsSystemType>19</ATvvTypes:CustomsSystemType>\r\n" +
				"            <ATvvTypes:Type>420</ATvvTypes:Type>\r\n" +
				"            <ATvvTypes:Number>10115010/200618/В0000213</ATvvTypes:Number>\r\n" +
				"            <ATvvTypes:CustomsCode>10115010</ATvvTypes:CustomsCode>\r\n" +
				"            <ATvvTypes:CreateDateTime>2018-06-20T12:55:01+04:00</ATvvTypes:CreateDateTime>\r\n" +
				"            <ATvvTypes:DeliveryType>2</ATvvTypes:DeliveryType>\r\n" +
				"            <ATvvTypes:DeliveryDate>2019-06-20T12:51:13+04:00</ATvvTypes:DeliveryDate>\r\n" +
				"            <ATvvTypes:BlankNumber>10115010/200618/В0000213</ATvvTypes:BlankNumber>\r\n" +
				"            <ATvvTypes:DclNumber>10115010/200618/300000213</ATvvTypes:DclNumber>\r\n" +
				"            <ATvvTypes:StartDate>2018-06-20T12:55:01+04:00</ATvvTypes:StartDate>\r\n" +
				"            <ATvvTypes:StartCustomsCode>10115010</ATvvTypes:StartCustomsCode>\r\n" +
				"            <ATvvTypes:VersionDateTime>2018-06-20T12:55:02+04:00</ATvvTypes:VersionDateTime>\r\n" +
				"            <ATvvTypes:DocSendDate>2018-06-20T12:55:02+04:00</ATvvTypes:DocSendDate>\r\n" +
				"            <ATvvTypes:ApplicationName>АС \"ПП\"</ATvvTypes:ApplicationName>\r\n" +
				"            <ATvvTypes:ApplicactionVersion>11.0.0.9786</ATvvTypes:ApplicactionVersion>\r\n" +
				"            <ATvvTypes:AttachedVehicles>\r\n" +
				"                <ATvvTypes:LinkedVeh>\r\n" +
				"                    <ATvvTypes:VehicleRole>1</ATvvTypes:VehicleRole>\r\n" +
				"                    <ATvvTypes:Vehicle>\r\n" +
				"                        <ATvvTypes:StateSignID>EW645766</ATvvTypes:StateSignID>\r\n" +
				"                        <ATvvTypes:VINID>RE698YT457687096</ATvvTypes:VINID>\r\n" +
				"                        <ATvvTypes:Type>20</ATvvTypes:Type>\r\n" +
				"                        <ATvvTypes:MarkCode>666</ATvvTypes:MarkCode>\r\n" +
				"                        <ATvvTypes:Model>h400</ATvvTypes:Model>\r\n" +
				"                        <ATvvTypes:CategoryCode>B</ATvvTypes:CategoryCode>\r\n" +
				"                        <ATvvTypes:OfftakeYear>2015</ATvvTypes:OfftakeYear>\r\n" +
				"                        <ATvvTypes:EngineVolume>4000</ATvvTypes:EngineVolume>\r\n" +
				"                        <ATvvTypes:NationalityCode>AT</ATvvTypes:NationalityCode>\r\n" +
				"                        <ATvvTypes:RunningPeriod>2</ATvvTypes:RunningPeriod>\r\n" +
				"                        <ATvvTypes:MaxWeight>4000</ATvvTypes:MaxWeight>\r\n" +
				"                        <ATvvTypes:MinWeight>2000</ATvvTypes:MinWeight>\r\n" +
				"                        <ATvvTypes:OperationStatus>0</ATvvTypes:OperationStatus>\r\n" +
				"                        <ATvvTypes:OperationStatusNote>Операция открытия временного ввоза транспортного средства, срок временного ввоза не нарушен</ATvvTypes:OperationStatusNote>\r\n" +
				"                        <ATvvTypes:Owner>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Z_address>Москва, 16-я Парковая</ATCompTypes:Z_address>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                            <ATCompTypes:NationalityCountryCode>AT</ATCompTypes:NationalityCountryCode>\r\n" +
				"                        </ATvvTypes:Owner>\r\n" +
				"                        <ATvvTypes:Driver>\r\n" +
				"                            <ATCompTypes:Type>Ф</ATCompTypes:Type>\r\n" +
				"                            <ATCompTypes:IdentityDocCode>02</ATCompTypes:IdentityDocCode>\r\n" +
				"                            <ATCompTypes:IdentityDocNumber>545766976</ATCompTypes:IdentityDocNumber>\r\n" +
				"                            <ATCompTypes:IdentityDocDate>2017-07-14T00:00:00+04:00</ATCompTypes:IdentityDocDate>\r\n" +
				"                            <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                            <ATCompTypes:AddressLine>AB, 458765, Сухум, Сухум, 16-я Парковая</ATCompTypes:AddressLine>\r\n" +
				"                            <ATCompTypes:Z_address>Москва, 16-я Парковая</ATCompTypes:Z_address>\r\n" +
				"                            <ATCompTypes:Surname>ИВАНОВ</ATCompTypes:Surname>\r\n" +
				"                            <ATCompTypes:Name>ИВАН</ATCompTypes:Name>\r\n" +
				"                            <ATCompTypes:Patronymicname>ИВАНОВИЧ</ATCompTypes:Patronymicname>\r\n" +
				"                            <ATCompTypes:Address xmlns:ATCompTypes=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:10.0\">\r\n" +
				"                                <ATCompTypes:PostalCode>458765</ATCompTypes:PostalCode>\r\n" +
				"                                <ATCompTypes:CountryCode>AB</ATCompTypes:CountryCode>\r\n" +
				"                                <ATCompTypes:Region>Сухум</ATCompTypes:Region>\r\n" +
				"                                <ATCompTypes:City>Сухум</ATCompTypes:City>\r\n" +
				"                                <ATCompTypes:StreetHouse>16-я Парковая</ATCompTypes:StreetHouse>\r\n" +
				"                            </ATCompTypes:Address>\r\n" +
				"                            <ATCompTypes:BirthDay>1990-07-14T00:00:00+04:00</ATCompTypes:BirthDay>\r\n" +
				"                            <ATCompTypes:BirthPlace>Австрия, Вена</ATCompTypes:BirthPlace>\r\n" +
				"                            <ATCompTypes:NationalityCountryCode>AT</ATCompTypes:NationalityCountryCode>\r\n" +
				"                        </ATvvTypes:Driver>\r\n" +
				"                    </ATvvTypes:Vehicle>\r\n" +
				"                </ATvvTypes:LinkedVeh>\r\n" +
				"            </ATvvTypes:AttachedVehicles>\r\n" +
				"            <ATvvTypes:StartInspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"            </ATvvTypes:StartInspectorLNP>\r\n" +
				"            <ATvvTypes:InspectorLNP>\r\n" +
				"                <ATCompTypes:Number>111</ATCompTypes:Number>\r\n" +
				"                <ATCompTypes:CustomsCode>10115010</ATCompTypes:CustomsCode>\r\n" +
				"                <ATCompTypes:Name>Test Test</ATCompTypes:Name>\r\n" +
				"            </ATvvTypes:InspectorLNP>\r\n" +
				"            <ATvvTypes:CustomsNote/>\r\n" +
				"        </ATvvTypes:VVinf>\r\n" +
				"    </Body>\r\n" +
				"</Envelope>\r\n";

		this.modifyXML= generateData(Vvinf);
		return this.modifyXML;
	}

	public String transformateXML11() {
		String ResultTKOUT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2001/06/soap-envelope\"\r\n" +
				"               xmlns:app=\"urn:customs.ru:Envelope:ApplicationInf:1.0\"\r\n" +
				"               xmlns:att=\"urn:customs.ru:Envelope:Attachments:1.0\"\r\n" +
				"               xmlns:roi=\"urn:customs.ru:Envelope:RoutingInf:1.0\"\r\n" +
				"               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
				"               xmlns:tpo=\"http://tpotr.obesp.contact-soft.ru\"\r\n" +
				"               xmlns:adp=\"http://adapters.order.contact-soft.ru\">\r\n" +
				"   <soap:Header>\r\n" +
				"      <roi:RoutingInf>\r\n" +
				"         <roi:EnvelopeID>43cf655d-60e6-4a8c-a45b-b1f94ccf91e9</roi:EnvelopeID>\r\n" +
				"         <roi:SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</roi:SenderInformation>\r\n" +
				"         <roi:ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</roi:ReceiverInformation>\r\n" +
				"         <roi:PreparationDateTime>2017-07-07T04:33:47Z</roi:PreparationDateTime>\r\n" +
				"         <roi:Priority>4</roi:Priority>\r\n" +
				"      </roi:RoutingInf>\r\n" +
				"      <app:ApplicationInf>\r\n" +
				"         <app:SoftKind>ContactSoft.TpoClient</app:SoftKind>\r\n" +
				"         <app:MessageKind>I403</app:MessageKind>\r\n" +
				"      </app:ApplicationInf>\r\n" +
				"      <q1:ATHeader xmlns:q1=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">\r\n" +
				"         <q1:ToCustomsCode>10000000</q1:ToCustomsCode>\r\n" +
				"         <q1:ToCustomsSystem>0</q1:ToCustomsSystem>\r\n" +
				"         <q1:FromCustomsCode>10717040</q1:FromCustomsCode>\r\n" +
				"         <q1:FromCustomsSystem>17</q1:FromCustomsSystem>\r\n" +
				"      </q1:ATHeader>\r\n" +
				"   </soap:Header>\r\n" +
				"   <soap:Body>\r\n" +
				"      <TPOinf xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
				"              xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\r\n" +
				"              xmlns=\"urn:customs.ru:Information:AutoTransport:ATtpoTypes:9.0\">\r\n" +
				"         <CustomsSystemType>17</CustomsSystemType>\r\n" +
				"         <Type>403</Type>\r\n" +
				"         <Number>10717040/070714/ТС-0839423</Number>\r\n" +
				"         <CustomsCode>10717040</CustomsCode>\r\n" +
				"         <CreateDateTime>2017-07-07T15:33:44+11:00</CreateDateTime>\r\n" +
				"         <BlankNumber>ТС-0839423</BlankNumber>\r\n" +
				"         <VersionDateTime>2017-07-07T00:00:00+11:00</VersionDateTime>\r\n" +
				"         <DocSendDate>2017-07-07T00:00:00+11:00</DocSendDate>\r\n" +
				"         <ApplicationName>АИС Ордер</ApplicationName>\r\n" +
				"         <ApplicactionVersion>7.1.0.5</ApplicactionVersion>\r\n" +
				"         <AttachedVehicles>\r\n" +
				"            <LinkedVeh>\r\n" +
				"               <VehicleRole>1</VehicleRole>\r\n" +
				"               <Vehicle>\r\n" +
				"                  <VINID>ОТСУТСТВУЕТ</VINID>\r\n" +
				"                  <TNVEDCode>8703219098</TNVEDCode>\r\n" +
				"                  <Model>DAIHATSU MOVE</Model>\r\n" +
				"                  <OfftakeYear>2009</OfftakeYear>\r\n" +
				"                  <EnginePowerHp>58</EnginePowerHp>\r\n" +
				"                  <EnginePowerKvt>42.66</EnginePowerKvt>\r\n" +
				"                  <EngineVolume>658</EngineVolume>\r\n" +
				"                  <ChassisID>ОТСУТСТВУЕТ</ChassisID>\r\n" +
				"                  <BodyID>L175S-0294756</BodyID>\r\n" +
				"                  <TamStoim>119682.15</TamStoim>\r\n" +
				"                  <SumEdSt>46079.38</SumEdSt>\r\n" +
				"                  <TamOform>500</TamOform>\r\n" +
				"                  <TamPlat>46579.38</TamPlat>\r\n" +
				"                  <FSValKod>643</FSValKod>\r\n" +
				"                  <Owner>\r\n" +
				"                     <Type xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">Ф</Type>\r\n" +
				"                     <IdentityDocCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">21</IdentityDocCode>\r\n" +
				"                     <IdentityDocNumber xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">989688    </IdentityDocNumber>\r\n" +
				"                     <IdentityDocSeries xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">08 10      </IdentityDocSeries>\r\n" +
				"                     <CountryCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">392</CountryCode>\r\n" +
				"                     <INNID xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">272402630623</INNID>\r\n" +
				"                     <AddressLine xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">Г.ХАБАРОВСК УЛ.БОЛЬШАЯ 95-134</AddressLine>\r\n" +
				"                     <SOATOID xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">08  </SOATOID>\r\n" +
				"                     <Surname xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">БЕРЕЖНАЯ</Surname>\r\n" +
				"                     <Name xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">ИРИНА</Name>\r\n" +
				"                     <Patronymicname xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">ВАСИЛЬЕВНА</Patronymicname>\r\n" +
				"                  </Owner>\r\n" +
				"               </Vehicle>\r\n" +
				"            </LinkedVeh>\r\n" +
				"         </AttachedVehicles>\r\n" +
				"         <InspectorLNP>\r\n" +
				"            <Number xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">147</Number>\r\n" +
				"            <CustomsCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">10717040</CustomsCode>\r\n" +
				"         </InspectorLNP>\r\n" +
				"      </TPOinf>\r\n" +
				"   </soap:Body>\r\n" +
				"</soap:Envelope>";

		this.modifyXML= generateData(ResultTKOUT);
		return this.modifyXML;
	}

	public String transformateXML12() {
		String LotDeparture = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
				"<soap:Envelope xmlns:soap=\"http://www.w3.org/2001/06/soap-envelope\" xmlns:app=\"urn:customs.ru:Envelope:ApplicationInf:1.0\" xmlns:att=\"urn:customs.ru:Envelope:Attachments:1.0\" xmlns:roi=\"urn:customs.ru:Envelope:RoutingInf:1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tpo=\"http://tpotr.obesp.contact-soft.ru\" xmlns:adp=\"http://adapters.order.contact-soft.ru\">\r\n" +
				"  <soap:Header>\r\n" +
				"    <roi:RoutingInf>\r\n" +
				"      <roi:EnvelopeID>9c63b0d8-d902-4016-9626-7d07351fdc74</roi:EnvelopeID>\r\n" +
				"          <roi:SenderInformation>wmq://RU.FTS.GNIVC/KPSPP.INCOME</roi:SenderInformation>\r\n" +
				"         <roi:ReceiverInformation>wmq://RU.FTS.GNIVC/AT.INCOME</roi:ReceiverInformation>\r\n" +
				"       <roi:PreparationDateTime>2017-09-13T09:36:30Z</roi:PreparationDateTime>\r\n" +
				"      <roi:Priority>4</roi:Priority>\r\n" +
				"    </roi:RoutingInf>\r\n" +
				"    <app:ApplicationInf>\r\n" +
				"      <app:SoftKind>ContactSoft.TpoClient</app:SoftKind>\r\n" +
				"      <app:MessageKind>I405</app:MessageKind>\r\n" +
				"    </app:ApplicationInf>\r\n" +
				"    <q1:ATHeader xmlns:q1=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">\r\n" +
				"      <q1:ToCustomsCode>10000000</q1:ToCustomsCode>\r\n" +
				"      <q1:ToCustomsSystem>0</q1:ToCustomsSystem>\r\n" +
				"      <q1:FromCustomsCode>10002000</q1:FromCustomsCode>\r\n" +
				"      <q1:FromCustomsSystem>17</q1:FromCustomsSystem>\r\n" +
				"    </q1:ATHeader>\r\n" +
				"  </soap:Header>\r\n" +
				"  <soap:Body>\r\n" +
				"    <PSPinf xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"urn:customs.ru:Information:AutoTransport:ATpspTypes:9.0\">\r\n" +
				"      <CustomsSystemType>17</CustomsSystemType>\r\n" +
				"      <Type>405</Type>\r\n" +
				"      <Number>11ПР123456</Number>\r\n" +
				"      <CustomsCode>10002000</CustomsCode>\r\n" +
				"      <CreateDateTime>2017-09-12T00:00:00</CreateDateTime>\r\n" +
				"      <BlankNumber>11ПР123456</BlankNumber>\r\n" +
				"      <VersionDateTime>2017-09-13T13:44:44.4277343+04:00</VersionDateTime>\r\n" +
				"      <DocSendDate>2017-09-13T13:44:44.4277343+04:00</DocSendDate>\r\n" +
				"      <ApplicationName>АИС Ордер</ApplicationName>\r\n" +
				"      <ApplicactionVersion>4.1.0.4</ApplicactionVersion>\r\n" +
				"      <AttachedVehicles>\r\n" +
				"        <LinkedVeh>\r\n" +
				"          <VehicleRole>1</VehicleRole>\r\n" +
				"          <Vehicle>\r\n" +
				"            <VINID>NISSAN12345678923</VINID>\r\n" +
				"            <TNVEDCode>8703229098</TNVEDCode>\r\n" +
				"            <Type>20</Type>\r\n" +
				"            <Model>NISSAN NOTE</Model>\r\n" +
				"   <CategoryCode>B</CategoryCode> \r\n" +
				"            <OfftakeYear>2008</OfftakeYear>\r\n" +
				"            <EngineVolume>1200</EngineVolume>\r\n" +
				"   <EnginePowerKvt>70</EnginePowerKvt>\r\n" +
				"            <EngineModel>NISSAN12</EngineModel>\r\n" +
				"            <EngineID>NISSAN12</EngineID>\r\n" +
				"            <EngineType>1</EngineType>\r\n" +
				"            <ChassisID>NISSAN12345678923</ChassisID>\r\n" +
				"            <BodyID>NISSAN12345678923</BodyID>\r\n" +
				"            <ColorCode>653</ColorCode>\r\n" +
				"   <MaxWeight>2000</MaxWeight> \r\n" +
				"   <MinWeight>1571</MinWeight> \r\n" +
				"            <ManufacturerCountryCode>392</ManufacturerCountryCode>\r\n" +
				"            <ManufacturerName>NISSAN</ManufacturerName>\r\n" +
				"            <FromCountryCode>392</FromCountryCode>\r\n" +
				"            <EcoClass>3</EcoClass>\r\n" +
				"            <Owner>\r\n" +
				"              <Type xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">Ф</Type>\r\n" +
				"              <IdentityDocCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">21</IdentityDocCode>\r\n" +
				"              <IdentityDocNumber xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">123456    </IdentityDocNumber>\r\n" +
				"              <IdentityDocSeries xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">65 03      </IdentityDocSeries>\r\n" +
				"              <IdentityDocGiver xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">ОВД Г. КРАСНОТУРИНСКА</IdentityDocGiver>\r\n" +
				"              <CountryCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">643</CountryCode>\r\n" +
				"              <AddressLine xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">Г. КРАСНОТУРИНСК УЛИЦА ЛЕНИНА ДОМ 47 КВАРТИРА 74</AddressLine>\r\n" +
				"              <SOATOID xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">65  </SOATOID>\r\n" +
				"              <Surname xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">ПИСЦОВ</Surname>\r\n" +
				"              <Name xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">ИВАН</Name>\r\n" +
				"              <Patronymicname xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">СЕРГЕЕВИЧ</Patronymicname>\r\n" +
				"            </Owner>\r\n" +
				"            <Approval />\r\n" +
				"          </Vehicle>\r\n" +
				"        </LinkedVeh>\r\n" +
				"      </AttachedVehicles>\r\n" +
				"      <InspectorLNP>\r\n" +
				"        <Number xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">9999</Number>\r\n" +
				"        <CustomsCode xmlns=\"urn:customs.ru:Information:AutoTransport:ATCompTypes:9.0\">10002000</CustomsCode>\r\n" +
				"      </InspectorLNP>\r\n" +
				"      <CustomsNote />\r\n" +
				"    </PSPinf>\r\n" +
				"  </soap:Body>\r\n" +
				"</soap:Envelope>";

		this.modifyXML= generateData(LotDeparture);
		return this.modifyXML;

	}
	private String generateData(String operation) {

		//rinfEnvelopeId
		//<rinf:EnvelopeID>F0EA3ADA-F753-419E-A49C-D4C6CB642E59</rinf:EnvelopeID>
		java.util.regex.Pattern patRinfEnvId = java.util.regex.Pattern.compile("rinf:EnvelopeID>.*</rinf:EnvelopeID");
		Matcher matchRinfId = patRinfEnvId.matcher(operation);
		//UUID rinfEnvId = UUID.randomUUID();
		envId = UUID.randomUUID();
		String templRinfId = matchRinfId.replaceAll("rinf:EnvelopeID>"+envId+ "</rinf:EnvelopeID");


		//envelopeID
		patEnvID = java.util.regex.Pattern.compile("EnvelopeID>.*</EnvelopeID");
		Matcher matchEnvId = patEnvID.matcher(templRinfId);
		//envId = UUID.randomUUID();
		String newTemplateEnvId = matchEnvId.replaceAll("EnvelopeID>"+envId+"</EnvelopeID");


		//MQId
		//if (this.modifyXML.contains("MessageID>")) {
		java.util.regex.Pattern pat1 = java.util.regex.Pattern.compile("MessageID>.*</MessageID");
		Matcher match1 = pat1.matcher(newTemplateEnvId);
		uuid1 = UUID.randomUUID();

		String newTemplate = match1.replaceAll("MessageID>" + uuid1 + "</MessageID");

		//user
		String randomArray1[] = {"exorciamus", "te", "omnis", "immundus", "spiritus",
				"omnis", "satanica", "potestas","omnis",
				"incursio", "infernalis", "adversaari", "omnis", "legio",
				"omnis", "congretario", "et", "secta", "diabolica",
				"in", "nomine", "virtute", "Domini", "Nostri", "Jesu", "Christi"};
		int arrayLength = randomArray1.length;
		int rand1 = (int) (Math.random()*arrayLength);

		java.util.regex.Pattern pat2 = java.util.regex.Pattern.compile("User>.*<");
		Matcher match2 = pat2.matcher(newTemplate);
		String newTemplate2 = match2.replaceAll("User>" + randomArray1[rand1]+"<");

		//date()
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat formatForDb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		dateDb = formatForDb.format(new Date());
		java.util.regex.Pattern pat3 = java.util.regex.Pattern.compile("PreparationDateTime>.*<");
		Matcher match3 = pat3.matcher(newTemplate2);
		date = simpleFormat.format(new Date());
		String newTemplate3 = match3.replaceAll("PreparationDateTime>" + date  +"<");

		//country
		String arrayCountries[] = {"Республика Армения", "Республика Беларусь", "Республика Казахстан", "Кыргызская Республика",
				"Российская Федерация", "Эстония", "Литва", "Латвия", "Франция", "Италия", "Нидерланды", "США"};
		int countryLenght = arrayCountries.length;
		int randCountry = (int) (Math.random()*countryLenght);
		java.util.regex.Pattern patCountry = java.util.regex.Pattern.compile("CounryName>.*<");
		Matcher matchCountry = patCountry.matcher(newTemplate3);
		String newTemplate4 = matchCountry.replaceAll("CounryName>" + arrayCountries[randCountry]+"<");

		//WorkNode
		java.util.regex.Pattern patWorkNode = java.util.regex.Pattern.compile("WorkNode>.*<");
		Matcher matchWorkNode = patWorkNode.matcher(newTemplate4);
		String newTemplate5 = matchWorkNode.replaceAll("WorkNode>" +randomWorknodes() +"<");

		//PostalCode
		Random rnd = new Random();
		int postCode = 100000 + rnd.nextInt(900000);
		java.util.regex.Pattern patPostalCode = java.util.regex.Pattern.compile("PostalCode>.*<");
		Matcher matchPostalCode = patPostalCode.matcher(newTemplate5);
		String newTemplate6 = matchPostalCode.replaceAll("PostalCode>" + postCode +"<");

		//City
		String arrayCities[] = {"Москва","Абрамцево","Алабино","Апрелевка","Архангельское","Ашитково","Байконур","Бакшеево","Балашиха","Барыбино","Белоомут","Белые Столбы","Бородино","Бронницы","Быково","Валуево","Вербилки","Верея","Видное","Внуково","Вождь Пролетариата","Волоколамск","Вороново","Воскресенск","Восточный","Востряково","Высоковск","Голицино","Деденево","Дедовск","Джержинский","Дмитров","Долгопрудный","Домодедово","Дорохово","Дрезна","Дубки","Дубна","Егорьевск","Железнодорожный","Жилево","Жуковский","Загорск","Загорянский","Запрудная","Зарайск","Звенигород","Зеленоград","Ивантеевка","Икша","Ильинский","Истра","Калининград","Кашира","Керва","Климовск","Клин","Клязьма","Кожино","Кокошкино","Коломна","Колюбакино","Королев","Косино","Котельники","Красково","Красноармейск","Красногорск","Краснозаводск","Краснознаменск","Красный Ткач","Крюково","Кубинка","Купавна","Куровское","Лесной Городок","Ликино-Дулево","Лобня","Лопатинский",
				"Лосино-Петровский","Лотошино","Лукино","Луховицы","Лыткарино","Львовский","Люберцы","Малаховка","Михайловское","Михнево","Можайск","Монино","Муханово","Мытищи","Нарофоминск","Нахабино","Некрасовка","Немчиновка","Новобратцевский","Новоподрезково","Ногинск","Обухово","Одинцово","Ожерелье","Озеры","Октябрьский","Опалиха","Орехово-Зуево","Павловский Посад","Первомайский","Пески","Пироговский","Подольск",
				"Полушкино","Правдинский","Привокзальный","Пролетарский","Протвино","Пушкино","Пущино","Радовицкий","Раменское","Реутов","Решетниково","Родники","Рошаль","Рублево","Руза","Салтыковка","Северный","Сергиев Посад","Серебряные Пруды","Серпухов","Солнечногорск","Солнцево","Софрино","Старая Купавна","Старбеево","Ступино","Сходня","Талдом","Текстильщик","Темпы","Тишково","Томилино","Троицк","Туголесский Бор","Тучково","Уваровка","Удельная","Успенское","Фирсановка","Фосфоритный","Фрязино","Фряново","Химки","Хорлово","Хотьково","Черкизово","Черноголовка","Черусти","Чехов","Шарапово","Шатура","Шатурторф","Шаховская","Шереметьевский","Щелково","Щербинка","Электрогорск","Электросталь","Электроугли","Яхрома","Санкт-Петербург","Александровская","Бокситогорск","Большая Ижора","Будогощь","Вознесенье","Волосово","Волхов","Всеволожск","Выборг","Вырица","Высоцк","Гатчина",
				"Дружная Горка","Дубровка","Ефимовский","Зеленогорск","Ивангород","Каменногорск","Кикерино","Кингисепп","Кириши","Кировск","Кобринское","Колпино","Коммунар","Кронштадт","Лисий Нос","Лодейное Поле","Ломоносов","Луга","Павловск","Парголово","Петродворец","Пикалёво","Подпорожье","Приозерск","Пушкин","Сестрорецк","Сланцы",
				"Сосновый Бор","Тихвин","Тосно","Шлиссельбург","Адыгейск","Майкоп","Акташ","Акутиха","Алейск","Алтайский","Баево","Барнаул","Белово","Белокуриха","Белоярск","Бийск","Благовещенск","Боровлянка","Бурла","Бурсоль","Волчиха","Горно-Алтайск","Горняк","Ельцовка","Залесово","Заринск","Заток","Змеиногорск","Камень-на-Оби","Ключи",
				"Кош-Агач","Красногорское","Краснощеково","Кулунда","Кытманово","Мамонтово","Новичиха","Новоалтайск","Онгудай","Павловск","Петропавловское","Поспелиха","Ребриха","Родино","Рубцовск","Славгород","Смоленское","Солонешное","Солтон","Староаллейское","Табуны","Тальменка","Топчиха","Троицкое","Турочак","Тюменцево","Угловское","Усть-Калманка","Усть-Кан","Усть-Кокса","Усть-Улаган","Усть-Чарышская Пристань","Хабары","Целинное","Чарышское","Шебалино","Шелаболиха","Шипуново","Айгунь","Архара","Белогорск","Благовещенск (Амурская обл.)","Бурея","Возжаевка","Екатеринославка","Ерофей Павлович","Завитинск","Зея","Златоустовск","Ивановка","Коболдо","Магдагачи","Новобурейский","Поярково","Райчихинск","Ромны","Свободный","Серышево","Сковородино","Стойба","Тамбовка","Тында","Шимановск","Экимчан","Ядрино","Амдерма","Архангельск","Березник","Вельск","Верхняя Тойма","Волошка","Вычегодский","Емца","Илеза","Ильинско-Подомское","Каргополь","Карпогоры","Кодино","Коноша","Коряжма","Котлас","Красноборск","Лешуконское","Мезень","Мирный","Нарьян-Мар","Новодвинск","Няндома","Онега","Пинега","Плесецк","Северодвинск","Сольвычегодск","Холмогоры","Шенкурск","Яренск","Астрахань","Ахтубинск","Верхний Баскунчак","Володарский","Енотаевка","Икряное","Камызяк","Капустин Яр","Красный Яр","Лиман","Началово","Харабали","Черный Яр","Аксаково","Амзя","Аскино","Баймак","Бакалы","Белебей","Белорецк","Бижбуляк",
				"Бирск","Благовещенск","Большеустьикинское","Бураево","Верхнеяркеево","Верхние Киги","Верхние Татышлы","Верхний Авзян","Давлеканово","Дуван","Дюртюли","Ермекеево","Ермолаево","Зилаир","Зирган","Иглино","Инзер","Исянгулово","Ишимбай","Кананикольское","Кандры","Караидель","Караидельский","Киргиз-Мияки",
				"Красноусольский","Кумертау","Кушнаренково","Малояз","Мелеуз","Месягутово","Мраково","Нефтекамск","Октябрьский","Раевский","Салават","Сибай","Старобалтачево","Старосубхангулово","Стерлибашево","Стерлитамак","Туймазы","Уфа","Учалы","Федоровка","Чекмагуш","Чишмы","Шаран","Янаул","Алексеевка","Белгород","Борисовка","Валуйки","Вейделевка","Волоконовка","Грайворон","Губкин","Ивня","Короча","Красногвардейское","Новый Оскол","Ракитное","Ровеньки","Старый Оскол","Строитель","Чернянка","Шебекино","Алтухово","Белая Березка","Белые Берега","Большое Полпино","Брянск","Бытошь","Выгоничи","Вышков","Гордеевка","Дубровка","Дятьково","Жирятино","Жуковка","Злынка","Ивот","Карачев","Клетня","Климово","Клинцы","Кокаревка","Комаричи","Красная Гора","Локоть","Мглин","Навля","Новозыбков","Погар","Почеп","Ржаница","Рогнедино","Севск","Стародуб","Суземка","Сураж","Трубчевск","Унеча","Бабушкин","Багдарин","Баргузин","Баянгол","Бичура","Выдрино","Гусиное Озеро","Гусиноозерск","Заиграево","Закаменск","Иволгинск","Илька","Кабанск","Каменск","Кижинга","Курумкан","Кырен","Кяхта","Монды","Мухоршибирь","Нижнеангарск","Орлик","Петропавловка","Романовка","Северобайкальск","Селенгинск","Сосново-Озерское","Таксимо","Турунтаево","Улан-Удэ","Хоринск","Александров","Андреево","Анопино","Бавлены","Балакирево","Боголюбово","Великодворский","Вербовский",
				"Владимир","Вязники","Городищи","Гороховец","Гусевский","Гусь Хрустальный","Золотково","Иванищи","Камешково","Карабаново","Киржач","Ковров","Кольчугино","Красная Горбатка","Меленки","Муром","Петушки","Покров","Собинка","Судогда","Суздаль","Юрьев-Польский","Алексеевская","Алущевск","Быково","Волгоград","Волжский","Городище","Дубовка","Елань","Жирновск","Иловля","Калач-на-Дону","Камышин","Кириллов","Клетский","Котельниково","Котово","Кумылженская","Ленинск","Михайловка","Нехаевский","Николаевск","Новоаннинский","Новониколаевский","Ольховка","Палласовка","Рудня","Светлый Яр","Серафимович","Средняя Ахтуба","Сталинград","Старая Полтавка","Суровикино","Урюпинск","Фролово","Чернышковский","Бабаево","Белозерск","Великий Устюг","Верховажье","Вожега","Вологда","Вохтога","Вытегра","Грязовец","Кадников","Кадуй","Кичменгский Городок","Липин Бор","Никольск","Нюксеница","Сокол","Сямжа","Тарногский Городок","Тотьма","Устюжна","Харовск","Чагода","Череповец","Шексна","Шуйское","Анна","Бобров","Богучар","Борисоглебск","Бутурлиновка","Верхний Мамон","Верхняя Хава","Воробьевка","Воронеж","Грибановский","Давыдовка","Елань-Коленовский","Калач","Кантемировка","Лиски","Нижнедевицк","Новая Усмань","Новохоперск","Ольховатка","Острогожск","Павловск","Панино","Петропавловка","Поворино","Подгоренский","Рамонь","Репьевка","Россошь","Семилуки","Таловая","Терновка","Хохольский","Эртиль","нововоронеж","Агвали","Акуша","Ахты","Ачису","Бабаюрт","Бежта","Ботлих","Буйнакск","Вачи","Гергебиль","Гуниб","Дагестанские Огни","Дербент","Дылым","Ершовка","Избербаш","Карабудахкент","Карата","Каспийск","Касумкент","Кизилюрт","Кизляр","Кочубей","Кумух",
				"Курах","Магарамкент","Маджалис","Махачкала","Мехельта","Новолакское","Рутул","Советское","Тарумовка","Терекли-Мектеб","Тлярата","Тпиг","Уркарах","Хасавюрт","Хив","Хунзах","Цуриб","Южно-Сухокумск","Биробиджан","Архиповка","Верхний Ландех","Вичуга","Гаврилов Посад","Долматовский","Дуляпино","Заволжск","Заречный","Иваново","Иваньковский",
				"Ильинское-Хованское","Каминский","Кинешма","Комсомольск","Кохма","Лух","Палех","Пестяки","Приволжск","Пучеж","Родники","Савино","Сокольское","Тейково","Фурманов","Шуя","Южа","Юрьевец","Алексеевск","Алзамай","Алыгжер","Ангарск",
				"Артемовский","Атагай","Байкал","Байкальск","Балаганск","Баяндай","Бирюсинск","Бодайбо","Большая Речка","Большой Луг","Бохан","Братск","Видим","Витимский","Вихоревка","Еланцы","Ербогачен","Железногорск-Илимский","Жигалово","Забитуй","Залари","Звездный","Зима","Иркутск","Казачинское","Качуг","Квиток","Киренск","Куйтун","Култук","Кутулик","Мама","Нижнеудинск","Оса",
				"Саянск","Слюдянка","Тайшет","Тулун","Усолье-Сибирское","Усть-Илимск","Усть-Кут","Усть-Ордынский","Усть-Уда","Черемхово","Чунский","Шелехов","Баксан","Майский","Нальчик","Нарткала","Прохладный","Советское","Терек","Тырныауз","Чегем-Первый","Багратионовск","Балтийск","Гвардейск","Гурьевск","Гусев","Железнодорожный","Зеленоградск","Знаменск","Кёнигсберг","Калининград","Кенисберг","Краснознаменск","Мамоново","Неман","Нестеров","Озерск","Полесск","Правдинск","Светлогорск","Светлый","Славск","Советск","Черняховск","Аршань","Каспийский","Комсомольский","Малые Дербеты","Приютное","Советское","Троицкое","Утта","Цаган-Аман","Элиста","Юста","Яшалта","Яшкуль","Бабынино","Балабаново","Барятино","Белоусово","Бетлица","Боровск","Дугна","Дудоровский","Думиничи","Еленский","Жиздра","Износки","Калуга","Киров","Козельск","Кондрово","Людиново","Малоярославец","Медынь","Мещовск","Мосальск","Обнинск","Перемышль","Спас-Деменск","Сухиничи","Таруса","Ульяново","Ферзиково","Хвастовичи","Юхнов","Атласово","Аянка","Большерецк","Вилючинск","Елизово","Ильпырский","Каменское","Кировский","Ключи","Крапивная","Мильково","Никольское","Озерновский","Оссора","Палана","Парень","Пахачи","Петропавловск-Камчатский","Тигиль","Тиличики","Усть-Большерецк","Усть-Камчатск","Амбарный","Беломорск","Валаам","Вирандозеро","Гирвас","Деревянка","Идель","Ильинский","Импалахти","Калевала","Кемь","Кестеньга","Кондопога","Костомукша","Лахденпохья","Лоухи","Медвежьегорск","Муезерский","Олонец","Петрозаводск","Питкяранта","Повенец","Пряжа","Пудож","Сегежа","Сортавала","Софпорог","Суоярви","Анжеро-Судженск","Барзас","Белово","Белогорск","Березовский","Грамотеино","Гурьевск","Ижморский","Итатский","Калтан","Кедровка","Кемерово","Киселевск","Крапивинский","Ленинск-Кузнецкий","Мариинск","Междуреченск","Мыски","Новокузнецк","Осинники","Прокопьевск","Промышленная","Тайга","Таштагол","Тисуль","Топки"};
		int cityLenght = arrayCountries.length;
		int randCity = (int) (Math.random()*cityLenght);
		java.util.regex.Pattern patCity = java.util.regex.Pattern.compile("City>.*<");
		Matcher matchCity = patCity.matcher(newTemplate6);
		String newTemplate7 = matchCity.replaceAll("City>" + arrayCities[randCity]+"<");

		//VINID
		java.util.regex.Pattern patVinID = java.util.regex.Pattern.compile("VINID>.*<");
		Matcher matchVinId = patVinID.matcher(newTemplate7);
		String newTemplate8 = matchVinId.replaceAll("VINID>" + generatorVIN() +"<");

		//StreetHouse
		String arrayStreets[] = {"1 Мая, ул.","1-й, мкр.","1-й Терновый, пер.","1-й Ходынский, пр-д","1-я Изумрудная, ул.","1-я Лазурная, ул.","1-я Научная, ул.","1-я Парковая, ул.","10-летия Октября, ул.","10-я Парковая, ул.","11-й, мкр.","11-ти Героев Сапёров, аллея","11-я Парковая, ул.","12-я Парковая, ул.","13-я Парковая, ул.","14-я Парковая, ул.","15-я Парковая, ул.","16-я Парковая, ул.","1812 года, ул.","1905 года, ул.","2-й Капотни, кв-л","2-й Кленовый, пер.","2-й Тушинский, пр-д","2-й Ходынский, пр-д","2-я Вишнёвая, ул.","2-я Изумрудная, ул.","2-я Лазурная, ул.","2-я Научная, ул.","2-я Парковая, ул.","2-я Пятилетка, ул.",
				"26-ти Бакинских Комиссаров, ул.","3-й Кленовый, пер.","3-й микрорайон, мкр.","3-й Силикатный, пр-д",
				"3-й Ходынский, пр-д","3-я Вишнёвая, ул.","3-я Лазурная, ул.","3-я Научная, ул.","3-я Парковая, ул.","4-й Кленовый, пер.","4-й Ходынский, пр-д","4-я Вишнёвая, ул.","4-я Парковая, ул.","40 лет Октября, пр-т","40 лет Октября, ул.","474-й, пр-д","4803-й, пр-д","4807-й, пр-д","4921-й, пр-д","4922-й, пр-д","5-й Капотни, кв-л","5-я Вишнёвая, ул.","5-я Парковая, ул.","50 лет Октября, ул.","6-й Кленовый, пер.","6-я Вишнёвая, ул.","6-я Парковая, ул.","60-летия Октября, пр-т","60-летия СССР, пл.","684-й, пр-д","687-й, пр-д","7-я Вишнёвая, ул.","7-я Парковая, ул.","8 Марта, ул.","8 Марта 1-я, ул.","8 Марта 4-я, ул.","8-я Вишнёвая, ул.","8-я Парковая, ул.","800-летия Москвы, ул.","9 Мая, ул.","9-я Парковая, ул.",};
		int streetLength = arrayStreets.length;
		int randStreet = (int) (Math.random()*streetLength);
		java.util.regex.Pattern patVin = java.util.regex.Pattern.compile("StreetHouse>.*<");
		Matcher matchVin = patVin.matcher(newTemplate8);
		String newTemplate9 = matchVin.replaceAll("StreetHouse>" + arrayStreets[randStreet] +"<");

		//DocumentID
		java.util.regex.Pattern patDocId = java.util.regex.Pattern.compile("<DocumentID xmlns=\"\"urn:customs.ru:CommonAggregateTypes:5.10.0\"\">.*</DocumentID");
		Matcher matchDocId = patDocId.matcher(newTemplate9);
		UUID docID = UUID.randomUUID();
		String newTemplate10 = matchDocId.replaceAll("<DocumentID xmlns=\"\"urn:customs.ru:CommonAggregateTypes:5.10.0\"\">" + docID + "</DocumentID");

		//matchDocId
		//DocumentID xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:DocumentID
		java.util.regex.Pattern patCatDocId = java.util.regex.Pattern.compile("DocumentID xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:DocumentID");
		Matcher matchCatDocId = patCatDocId.matcher(newTemplate10);
		UUID docCatID = UUID.randomUUID();
		String newTemplate11 = matchCatDocId.replaceAll("DocumentID xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">" + docCatID + "</cat_ru:DocumentID");

		//Customscode
		//CustomsCode>10099030</CustomsCode
		Random rnd1 = new Random();
		int customsCode = 100000 + rnd1.nextInt(900000);
		java.util.regex.Pattern patCustCode = java.util.regex.Pattern.compile("CustomsCode>.*</CustomsCode");
		Matcher matchCustCode = patCustCode.matcher(newTemplate11);
		String newTemplate12 = matchCustCode.replaceAll("CustomsCode>"+ randomcustomCodes() + "</CustomsCode");

		//ExecutionDate
		//ExecutionDate>2017-09-18</ESADout_CU:ExecutionDate
		java.util.regex.Pattern patExecDate = java.util.regex.Pattern.compile("ExecutionDate>.*</ESADout_CU:ExecutionDate");
		Matcher matchPatExecdate = patExecDate.matcher(newTemplate12);
		String newTemplate13 = matchPatExecdate.replaceAll("ExecutionDate>" + randomDate() +"</ESADout_CU:ExecutionDate");

		//region
		//cat_ru:Region>.*</cat_ru:Region
		java.util.regex.Pattern patRegion = java.util.regex.Pattern.compile("cat_ru:Region>.*</cat_ru:Region");
		Matcher matchRegion = patRegion.matcher(newTemplate13);
		String arrayRegion[] = {"Золинген","Кёльн","Крефельд","Леверкузен","Липпштадт","Лунен","Люденсхейд","Малхейм-ан-дер-Рур","Марл","Минден","Монхенгладбах","Мюнстер","Нидеркассель","Ньюсс","Оберхаузен","Падерборн","Ратинген","Рейн","Ремшейд","Сест","Стендаль","Хаген","Хамм","Харт","Херн","Херфорд","Эскирхен","Эссен","Киль","Любек","Ноймюнстер","Фленсбург","Эльмсхорн","Апельдоорн","Арнхем","Нижмеген","Реден","Эд","Гронинген","Эммен","Венло","Керкрад","Маастрихт","Хеерлен","Альмело","Девентер","Хенгело","Эншед","Алькмаар","Амстельвеен","Амстердам","Велсен","Ден-Хельдер","Хаарлем","Хилверсум","Бреда","Тилбург","Хелмонд","Эйндховен","Амерсфоорт","Зейст","Утрехт","Лееварден","Влаардинген","Гуда","Дельфт","Дордрехт","Заандам","Зволь","Лейден","Роттердам","Хаге","Шевенинген","Шидам","Тегусигальпа","Гонконг","Патры","Афины","Пирей","Драма","Александруполис","Хания","Иоаннина","Ираклион","Кавалла","Волос","Фессалоники","Керкира","Авадхара","Гагра","Гали","Гудаута","Гульрипш","Новый Афон","Очамчиров","Пицунда","Сухуми","Ткварчели","Батуми","Кобулети","Хуло","Абастумани","Абаша","Агара","Адигени","Амбролаури","Аспиндза","Ахалкалаки","Ахалцихе","Ахмета","Бакуриани","Богдановка","Болниси","Боржоми","Вани","Гардабани","Гегечкори","Гори","Гурджаани","Джвари","Дманиси","Душети","Зестафони","Зугдиди","Казбеги","Карели","Каспи","Кваиси","Кварели","Коджори","Кутаиси","Лагодехи","Ланчхути","Ленингори","Лентехи","Марнеули","Махарадзе","Маяковский","Местиа","Они","Орджоникидзе","Пасанаури","Поти","Рустави","Сагареджо","Самтредиа","Сачхере","Сигнахи","Тбилиси","Телави","Тержола","Тетри-Цкаро","Тианети","Ткибули","Хашури","Хоби","Цагери","Цаленджиха","Цалка","Цители-Цкаро","Цнори","Цулукидзе","Цхакая","Цхалтубо","Чиатура","Чохатаури","Чхороцку","Джава","Цхинвали","Колдинг","Альборг","Орхус","Рандерс","Роскилде","Эсбйерг","Копенгаген","Оденс","Хельсингор","Санто-доминго","Каир","Асуан","Асыут","Бени Суэф","Танта","Думият","Александрия","Хургада","Шарм эль шейх","Иерусалим","Модиин","Бат-Ям","Герцелия","Гиватаим","Кирьят-Оно","Рамат-Ган","Рамат-Хашарон","Тель-Авив","Холон","Арад","Ашдод","Ашкелон","Бээр-Шева","Димона","Кирьят-Гат","Кирьят-Малахи","Эйлат","Ариэль","Бнэй-Брак","Гэдера","Кфар Саба","Лод","Натания","Нэс-Циона","Од-а Шарон","Пэтах-Тиква","Раанана","Рамла","Ришон-ЛеЦион","Рош-ха-Аин","Рэховот","Явне","Акко","Акко (порт)","Афула","Кармиэль","Кацрин","Кирьят-Ата","Кирьят-Ата","Кирьят-Тивон","Кирьят-Шмона","Мигдаль аЭмек","Нагария","Нацэрэт","Сахнин","Тверия","Хайфа","Цфат","Далият-Эль-Кармэль","Зихрон-Яаков","Кирьят-Бялик","Кирьят-Моцкин","Кирьят-Ям","Наария","Нэшэр","Ор-Акива","Пардэс-Ханна","Тират-Кармэль","Хэдэра","Асансол","Байдьябати","Балли","Банкура","Барасат","Бхатпара","Даржилинг","Дум-Дум","Дургапур","Камархати","Кришнанагар","Кхарагпур","Наихати","Панихати","Титагарх","Биласпур","Бхилаи","Дург","Раипур","Багалкот","Белгаум","Беллари","Бияпур","Гадаг","Давангер","Колар Голд Филдс","Мангалор","Раичур","Сагар","Тумкур","Хоспет","Хубли","Шимога","Дехра Дун","Сринагар","Адони","Анакапал","Анантапур","Визианагарам","Вияиавада","Гунтакал","Гунтур","Какинада","Куддапах","Мачилипатнам"};
		int regionLength = arrayRegion.length;
		int randRegion = (int) (Math.random()*regionLength);
		String newTemplate14 = matchRegion.replaceAll("cat_ru:Region>"+ arrayRegion[randRegion] + "</cat_ru:Region");


		//ogrn
		//OGRN>.*</cat_ru:OGRN
		java.util.regex.Pattern patOGRN = java.util.regex.Pattern.compile("OGRN>.*</cat_ru:OGRN");
		Matcher matcherOGRN = patOGRN.matcher(newTemplate14);
		String newTemplate15 = matcherOGRN.replaceAll("OGRN>" + randomOGRN() + "</cat_ru:OGRN");

		//inn
		//cat_ru:INN>.*</cat_ru:INN
		java.util.regex.Pattern patINN = java.util.regex.Pattern.compile("cat_ru:INN>.*</cat_ru:INN");
		Matcher matchINN = patINN.matcher(newTemplate15);
		String newTemplate16 = matchINN.replaceAll("cat_ru:INN>"+ randomINN()+ "</cat_ru:INN");

		//kpp
		//cat_ru:KPP>785050001</cat_ru:KPP
		java.util.regex.Pattern patKpp = java.util.regex.Pattern.compile("cat_ru:KPP>.*</cat_ru:KPP");
		Matcher matchKpp = patKpp.matcher(newTemplate16);
		String newTemplate17 = matchKpp.replaceAll("cat_ru:KPP>"+ randomKPP() + "</cat_ru:KPP");

		//officename
		//cat_ru:OfficeName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">т/п Морской порт Владивосток</cat_ru:OfficeName
		java.util.regex.Pattern patOfficeName = java.util.regex.Pattern.compile("cat_ru:OfficeName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:OfficeName");
		Matcher matchOfficeName = patOfficeName.matcher(newTemplate17);
		String newTemplate18 = matchOfficeName.replaceAll("cat_ru:OfficeName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+ randomOfficeName() + "</cat_ru:OfficeName");

		//goodsDescription
		//catESAD_cu:GoodsDescription xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:GoodsDescription
		java.util.regex.Pattern patGoodsDesc = java.util.regex.Pattern.compile("catESAD_cu:GoodsDescription xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:GoodsDescription");
		Matcher matchGoodsDesc = patGoodsDesc.matcher(newTemplate18);
		String newTemplate19 = matchGoodsDesc.replaceAll("catESAD_cu:GoodsDescription xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">"+ randomOfficeName() + randomKPP()+"</catESAD_cu:GoodsDescription");

		//GrossWeightQuantity
		//GrossWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">14869.4</catESAD_cu:GrossWeightQuantity
		java.util.regex.Pattern patGrossWeight = java.util.regex.Pattern.compile("GrossWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:GrossWeightQuantity");
		Matcher matchGrossWeight = patGrossWeight.matcher(newTemplate19);
		String newTemplate20 = matchGrossWeight.replaceAll("GrossWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">"+randomGrossWeight()+"</catESAD_cu:GrossWeightQuantity");


		//NetWeightQuantity
		//NetWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">12209.48</catESAD_cu:NetWeightQuantity
		java.util.regex.Pattern patNetWeight = java.util.regex.Pattern.compile("NetWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:NetWeightQuantity");
		Matcher matchNetWeight = patNetWeight.matcher(newTemplate20);
		String newTemplate21 = matchNetWeight.replaceAll("NetWeightQuantity xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">" + randomGrossWeight() + "</catESAD_cu:NetWeightQuantity");

		//InvoicedCost
		//InvoicedCost xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:InvoicedCost
		java.util.regex.Pattern patInvCost = java.util.regex.Pattern.compile("InvoicedCost xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:InvoicedCost");
		Matcher matchInvCost = patInvCost.matcher(newTemplate21);
		String newTemplate22 = matchInvCost.replaceAll("InvoicedCost xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">"+randomGrossWeight()+"</catESAD_cu:InvoicedCost");

		//PrDocumentDate
		//PrDocumentDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">2017-04-26</cat_ru:PrDocumentDate
		java.util.regex.Pattern patPrDocumentDate = java.util.regex.Pattern.compile("PrDocumentDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PrDocumentDate");
		Matcher matchPrDocumentData = patPrDocumentDate.matcher(newTemplate22);
		String newTemplate23 = matchPrDocumentData.replaceAll("PrDocumentDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+ randPrDate() + "</cat_ru:PrDocumentDate");


		//GoodsTNVEDCode
		//catESAD_cu:GoodsTNVEDCode xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:GoodsTNVEDCode
		java.util.regex.Pattern patGoodsTN = java.util.regex.Pattern.compile("catESAD_cu:GoodsTNVEDCode xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">.*</catESAD_cu:GoodsTNVEDCode");
		Matcher matchGoodsTN = patGoodsTN.matcher(newTemplate23);
		String newTemplate24 = matchGoodsTN.replaceAll("catESAD_cu:GoodsTNVEDCode xmlns:catESAD_cu=\"urn:customs.ru:CUESADCommonAggregateTypesCust:5.11.0\">"+ random11Length()+ "</catESAD_cu:GoodsTNVEDCode");


		//ContainerIdentificaror
		//catESAD_cu:ContainerIdentificaror>TCLU8307440</catESAD_cu:ContainerIdentificaror
		java.util.regex.Pattern patContID = java.util.regex.Pattern.compile("catESAD_cu:ContainerIdentificaror>.*</catESAD_cu:ContainerIdentificaror");
		Matcher matchContID = patContID.matcher(newTemplate24);
		String newTemplate25 = matchContID.replaceAll("catESAD_cu:ContainerIdentificaror>"+ generateContainerIdentificaror()+ "</catESAD_cu:ContainerIdentificaror");

		//PersonSurname
		//cat_ru:PersonSurname xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">ШВЕЦ</cat_ru:PersonSurname
		java.util.regex.Pattern patPersonSurname = java.util.regex.Pattern.compile("cat_ru:PersonSurname xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PersonSurname");
		Matcher matchPersonSurname = patPersonSurname.matcher(newTemplate25);
		String arraySurnames[] = {"Иванов", "Петров", "Сидоров", "Головашин", "Петренко", "Сидоренко", "Данилов", "Петросян", "Герасимов"};
		int surnamesLength = arraySurnames.length;
		int randSurname = (int) (Math.random()*surnamesLength);
		String newTemplate26 = matchPersonSurname.replaceAll("cat_ru:PersonSurname xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">" + arraySurnames[randSurname]+"</cat_ru:PersonSurname");


		//PersonName
		//cat_ru:PersonName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">АНДРЕЙ</cat_ru:PersonName
		java.util.regex.Pattern patPersonName = java.util.regex.Pattern.compile("cat_ru:PersonName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PersonName");
		Matcher matchPersonName = patPersonName.matcher(newTemplate26);
		String arrayNames[] = {"Иван", "Петр", "Сидор", "Дмитрий", "Всеволод", "Сидор", "Данил", "Петро", "Герасим"};
		int namesLength = arrayNames.length;
		int randName = (int) (Math.random()*namesLength);
		String newTemplate27 = matchPersonName.replaceAll("cat_ru:PersonName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">" + arrayNames[randName] + "</cat_ru:PersonName");

		//PersonMiddleName
		//cat_ru:PersonMiddleName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PersonMiddleName
		java.util.regex.Pattern patMiddleName = java.util.regex.Pattern.compile("cat_ru:PersonMiddleName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PersonMiddleName");
		Matcher matchMiddleName = patMiddleName.matcher(newTemplate27);
		String arrayMiddleNames[] = {"Иванович", "Петрович", "Сидорович", "Дмитриевич", "Всеволодович", "Павлович", "Данилович", "Максимович", "Герасимович"};
		int middleNamesLength = arrayMiddleNames.length;
		int randMiddleName = (int) (Math.random()*middleNamesLength);
		String newTemplate28 = matchMiddleName.replaceAll("cat_ru:PersonMiddleName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">" +arrayMiddleNames[randMiddleName]+"</cat_ru:PersonMiddleName");

		//PersonPost
		//cat_ru:PersonPost xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">НАЧАЛЬНИК БАЛТИЙСКОГО ОТДЕЛА ТАМОЖЕННОГО ОФОРМЛЕНИЯ</cat_ru:PersonPost
		java.util.regex.Pattern patPersonPost = java.util.regex.Pattern.compile("cat_ru:PersonPost xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PersonPost");
		Matcher matchPersonPost = patPersonPost.matcher(newTemplate28);
		String arrayPost[] = {"Начальник", "Большой начальник", "Очень большой начальник", "Начальничек", "Таможенник"};
		int postLength = arrayPost.length;
		int randPost = (int) (Math.random()*postLength);
		String newTemplate29 = matchPersonPost.replaceAll("cat_ru:PersonPost xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+ arrayPost[randPost] + "</cat_ru:PersonPost");

		//IdentityCard
		//cat_ru:IdentityCardCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">21</cat_ru:IdentityCardCode
		java.util.regex.Pattern patIdentCard = java.util.regex.Pattern.compile("cat_ru:IdentityCardCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardCode");
		Matcher matchIdentCard = patIdentCard.matcher(newTemplate29);
		String newTemplate30 = matchIdentCard.replaceAll("cat_ru:IdentityCardCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generateIdentCard()+"</cat_ru:IdentityCardCode");

		//IdentityCardName
		//cat_ru:IdentityCardName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">ПАСРФ</cat_ru:IdentityCardName
		java.util.regex.Pattern patIdentCardName = java.util.regex.Pattern.compile("cat_ru:IdentityCardName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardName");
		Matcher matchIdentCardName = patIdentCardName.matcher(newTemplate30);
		String newTemplate31 = matchIdentCardName.replaceAll("cat_ru:IdentityCardName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generatorPatIdentCardName()+"</cat_ru:IdentityCardName");


		//IdentityCardSeries
		//cat_ru:IdentityCardSeries xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">40 02</cat_ru:IdentityCardSeries
		java.util.regex.Pattern patIdentCardSeries = java.util.regex.Pattern.compile("cat_ru:IdentityCardSeries xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardSeries");
		Matcher matchIdentCardSeries = patIdentCardSeries.matcher(newTemplate31);
		String newTemplate32 = matchIdentCardSeries.replaceAll("cat_ru:IdentityCardSeries xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generateSeries()+"</cat_ru:IdentityCardSeries");


		//IdentityCardNumber
		//cat_ru:IdentityCardNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardNumber
		java.util.regex.Pattern patIdentCardNumber = java.util.regex.Pattern.compile("cat_ru:IdentityCardNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardNumber");
		Matcher matchIdentCardNumber = patIdentCardNumber.matcher(newTemplate32);
		String newTemplate33 = matchIdentCardNumber.replaceAll("cat_ru:IdentityCardNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generateNumber()+"</cat_ru:IdentityCardNumber");

		//IdentityCardDate
		//cat_ru:IdentityCardDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">2002-03-06</cat_ru:IdentityCardDate
		java.util.regex.Pattern patIdentCardDate = java.util.regex.Pattern.compile("cat_ru:IdentityCardDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:IdentityCardDate");
		Matcher matchIdentCardDate = patIdentCardDate.matcher(newTemplate33);
		String newTemplate34 = matchIdentCardDate.replaceAll("cat_ru:IdentityCardDate xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+randomDate()+"</cat_ru:IdentityCardDate");

		//OrganizationName
		//cat_ru:OrganizationName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:OrganizationName
		java.util.regex.Pattern patOrgName = java.util.regex.Pattern.compile("cat_ru:OrganizationName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:OrganizationName");
		Matcher matchOrgName = patOrgName.matcher(newTemplate34);
		String newTemplate35 = matchOrgName.replaceAll("cat_ru:OrganizationName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generateRandomText()+"</cat_ru:OrganizationName");


		//GoodsQuantity
		//catpi_ru:GoodsQuantity xmlns:catpi_ru=\"urn:customs.ru:Information:PriorInformation:PriorCommonAggregateTypes:5.11.0\">1164096</catpi_ru:GoodsQuantity
		java.util.regex.Pattern patGoodsQuan = java.util.regex.Pattern.compile("catpi_ru:GoodsQuantity xmlns:catpi_ru=\"urn:customs.ru:Information:PriorInformation:PriorCommonAggregateTypes:5.11.0\">.*</catpi_ru:GoodsQuantity");
		Matcher matchGoodsQuan = patGoodsQuan.matcher(newTemplate35);
		String newTemplate36 = matchGoodsQuan.replaceAll("catpi_ru:GoodsQuantity xmlns:catpi_ru=\"urn:customs.ru:Information:PriorInformation:PriorCommonAggregateTypes:5.11.0\">"+randomGoodsQuan()+"</catpi_ru:GoodsQuantity");


		//MeasureUnitQualifierCode
		//cat_ru:MeasureUnitQualifierCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">796</cat_ru:MeasureUnitQualifierCode
		java.util.regex.Pattern patMeasUnQualCode = java.util.regex.Pattern.compile("cat_ru:MeasureUnitQualifierCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:MeasureUnitQualifierCode");
		Matcher matchMeasQualCode = patMeasUnQualCode.matcher(newTemplate36);
		String newTemplate37 = matchMeasQualCode.replaceAll("cat_ru:MeasureUnitQualifierCode xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+randomMeasUnQualCode()+"</cat_ru:MeasureUnitQualifierCode");

		//PI_RegID
		//npir:PI_RegID>10702030-10-180917-0001-4</npir:PI_RegID
		java.util.regex.Pattern patPiRegId = java.util.regex.Pattern.compile("npir:PI_RegID>.*</npir:PI_RegID");
		Matcher matchPiRegId = patPiRegId.matcher(newTemplate37);
		String newTemplate38 = matchPiRegId.replaceAll("npir:PI_RegID>"+randomPiRegID()+"</npir:PI_RegID");

		//PI_RegDateTime
		//npir:PI_RegDateTime>2017-09-18T18:37:01</npir:PI_RegDateTime
		java.util.regex.Pattern patPiRegDateTime = java.util.regex.Pattern.compile("npir:PI_RegDateTime>.*</npir:PI_RegDateTime");
		Matcher matchPiRegDateTime = patPiRegDateTime.matcher(newTemplate38);
		String newTemplate39 = matchPiRegDateTime.replaceAll("npir:PI_RegDateTime>"+randDateNow()+"</npir:PI_RegDateTime");

		//InitialEnvelopeID
		//InitialEnvelopeID>b8c824d5-0acd-47b0-83bc-9d6545624f2b</InitialEnvelopeID
		java.util.regex.Pattern patInitEnvId = java.util.regex.Pattern.compile("InitialEnvelopeID>.*</InitialEnvelopeID");
		Matcher matchInitEnvId = patInitEnvId.matcher(newTemplate39);
		UUID initEnvId = UUID.randomUUID();
		String newTemplate40 = matchInitEnvId.replaceAll("InitialEnvelopeID>"+ initEnvId+"</InitialEnvelopeID");


		//RefDocumentID
		//RefDocumentID xmlns=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">1d26e70d-c658-4661-988d-10a52e5bab27</RefDocumentID
		java.util.regex.Pattern patRefDocId = java.util.regex.Pattern.compile("RefDocumentID xmlns=\"\"urn:customs.ru:CommonAggregateTypes:5.10.0\"\">.*</RefDocumentID");
		Matcher matchRefDocId  = patRefDocId.matcher(newTemplate40);
		UUID refDocUUID = UUID.randomUUID();
		String newTemplate41 = matchRefDocId.replaceAll("RefDocumentID xmlns=\"\"urn:customs.ru:CommonAggregateTypes:5.10.0\"\">"+refDocUUID+"</RefDocumentID");

		//RefDocumentID>1d26e70d-c658-4661-988d-10a52e5bab27</RefDocumentID
		java.util.regex.Pattern patRefDocumId = java.util.regex.Pattern.compile("RefDocumentID>.*</RefDocumentID");
		Matcher matchRefDocumId = patRefDocumId.matcher(newTemplate41);
		String newTemplate42 = matchRefDocumId.replaceAll("RefDocumentID>"+refDocUUID+"</RefDocumentID");

		//PackReceiveDate
		//ATCnfrm:PackReceiveDate>2018-06-20T08:57:23Z</ATCnfrm:PackReceiveDate
		java.util.regex.Pattern patPackReceiveDate = java.util.regex.Pattern.compile("ATCnfrm:PackReceiveDate>.*</ATCnfrm:PackReceiveDate");
		Matcher matchPackRec = patPackReceiveDate.matcher(newTemplate42);
		String newTemplate43 = matchPackRec.replaceAll("ATCnfrm:PackReceiveDate>" + dateDb +"</ATCnfrm:PackReceiveDate");

		//CreateDate>2017-09-21</CreateDate
		java.util.regex.Pattern patCreateDate = java.util.regex.Pattern.compile("CreateDate>.*</CreateDate");
		Matcher matchCreateDate = patCreateDate.matcher(newTemplate43);
		String newTemplate44 = matchCreateDate.replaceAll("CreateDate>"+randPrDate()+"</CreateDate");

		//CreateTime>13:36:07.8294964+03:00</CreateTime
		java.util.regex.Pattern patCreateTime = java.util.regex.Pattern.compile("CreateTime>.*</CreateTime");
		Matcher matchCreateTime = patCreateTime.matcher(newTemplate44);
		String newTemplate45 = matchCreateTime.replaceAll("CreateTime>" +randTimeNow()+ "</CreateTime");


		//DocCreateDateTimeLB
		//ATrq4Types:DocCreateDateTimeLB>2018-06-01T00:00:00</ATrq4Types:DocCreateDateTimeLB
		java.util.regex.Pattern patDocCreateDateLb = java.util.regex.Pattern.compile("ATrq4Types:DocCreateDateTimeLB>.*</ATrq4Types:DocCreateDateTimeLB");
		Matcher matchDocCreateDateLb = patDocCreateDateLb.matcher(newTemplate45);
		String newTemplate46 = matchDocCreateDateLb.replaceAll("ATrq4Types:DocCreateDateTimeLB>"+ randDateNow()+"</ATrq4Types:DocCreateDateTimeLB");


		//DocCreateDateTimeHB
		//ATrq4Types:DocCreateDateTimeHB>2018-06-07T14:11:55</ATrq4Types:DocCreateDateTimeHB
		java.util.regex.Pattern patDocCreateTimeHB = java.util.regex.Pattern.compile("ATrq4Types:DocCreateDateTimeHB>.*</ATrq4Types:DocCreateDateTimeHB");
		Matcher matchDocCreateTimeHB = patDocCreateTimeHB.matcher(newTemplate46);
		String newTemplate47 = matchDocCreateTimeHB.replaceAll("ATrq4Types:DocCreateDateTimeHB>"+ randTimeNow()+"</ATrq4Types:DocCreateDateTimeHB");


		//OperationID
		//ATvvTypes:OperationID>40b457c7-9e00-41d5-a08a-680dcc8bb62c</ATvvTypes:OperationID
		java.util.regex.Pattern patOpId = java.util.regex.Pattern.compile("ATvvTypes:OperationID>.*</ATvvTypes:OperationID");
		Matcher matchOpId = patOpId.matcher(newTemplate47);
		UUID opId = UUID.randomUUID();
		String newTemplate48 = matchOpId.replaceAll("ATvvTypes:OperationID>" + opId + "</ATvvTypes:OperationID");


		//Number
		//ATvvTypes:Number>10101020/050618/В0000003</ATvvTypes:Number
		java.util.regex.Pattern patNumber = java.util.regex.Pattern.compile("ATvvTypes:Number>.*</ATvvTypes:Number");
		Matcher matchNumber = patNumber.matcher(newTemplate48);
		String newTemplate49 = matchNumber.replaceAll("ATvvTypes:Number>"+randomNumber() +"</ATvvTypes:Number");


		//CreateDateTime
		//ATvvTypes:CreateDateTime>2018-06-05T15:43:53+03:00</ATvvTypes:CreateDateTime
		java.util.regex.Pattern patCreateDateTime = java.util.regex.Pattern.compile("ATvvTypes:CreateDateTime>.*</ATvvTypes:CreateDateTime");
		Matcher matchCreateDateTime = patCreateDateTime.matcher(newTemplate49);
		String newTemplate50 = matchCreateDateTime.replaceAll("ATvvTypes:CreateDateTime>"+ randDateNow()+"</ATvvTypes:CreateDateTime");

		//DeliveryDate
		//ATvvTypes:DeliveryDate>2019-06-05T15:09:50+03:00</ATvvTypes:DeliveryDate
		java.util.regex.Pattern patDeliveryDate = java.util.regex.Pattern.compile("ATvvTypes:DeliveryDate>.*</ATvvTypes:DeliveryDate");
		Matcher matchDeliveryDate  = patDeliveryDate.matcher(newTemplate50);
		String newTemplate51 = matchDeliveryDate.replaceAll("ATvvTypes:DeliveryDate>"+ randDateNow()+"</ATvvTypes:DeliveryDate");


		//BlankNumber
		//ATvvTypes:BlankNumber>10101020/050618/В0000003</ATvvTypes:BlankNumber
		java.util.regex.Pattern patBlankNumber = java.util.regex.Pattern.compile("ATvvTypes:Number>.*</ATvvTypes:Number");
		Matcher matchBlankNumber = patBlankNumber.matcher(newTemplate51);
		String newTemplate52 = matchBlankNumber.replaceAll("ATvvTypes:Number>"+randomNumber() +"</ATvvTypes:Number");


		//DclNumber
		//DclNumber>10101020/050618/В0000003</ATvvTypes:DclNumber
		java.util.regex.Pattern patDclNumber = java.util.regex.Pattern.compile("DclNumber>.*</ATvvTypes:DclNumber");
		Matcher matchDclNumber = patDclNumber.matcher(newTemplate52);
		String newTemplate53 = matchDclNumber.replaceAll("DclNumber>"+randomNumber()+"</ATvvTypes:DclNumber");

		//StartDate
		//ATvvTypes:StartDate>2018-06-05T15:43:53+03:00</ATvvTypes:StartDate
		java.util.regex.Pattern patStartDate = java.util.regex.Pattern.compile("ATvvTypes:StartDate>.*</ATvvTypes:StartDate");
		Matcher matchStartDate = patStartDate.matcher(newTemplate53);
		String newTemplate54 = matchStartDate.replaceAll("ATvvTypes:StartDate>"+ randomDate()+"T"+randTimeNow()+ "</ATvvTypes:StartDate");


		//VersionDateTime
		//ATvvTypes:VersionDateTime>2018-06-05T15:43:57+03:00</ATvvTypes:VersionDateTime
		java.util.regex.Pattern patVersionDateTime = java.util.regex.Pattern.compile("ATvvTypes:VersionDateTime>.*</ATvvTypes:VersionDateTime");
		Matcher matchVersionDateTime = patVersionDateTime.matcher(newTemplate54);
		String newTemplate55 = matchVersionDateTime.replaceAll("ATvvTypes:VersionDateTime>"+randomDate()+"T"+randTimeNow()+"</ATvvTypes:VersionDateTime");


		//DocSendDate
		//ATvvTypes:DocSendDate>2018-06-05T15:43:57+03:00</ATvvTypes:DocSendDate
		java.util.regex.Pattern patDocSendDate = java.util.regex.Pattern.compile("ATvvTypes:DocSendDate>.*</ATvvTypes:DocSendDate");
		Matcher matchDocSendDate = patDocSendDate.matcher(newTemplate55);
		String newTemplate56 = matchDocSendDate.replaceAll("ATvvTypes:DocSendDate>"+randomDate()+"T"+randTimeNow()+"</ATvvTypes:DocSendDate");

		//ATvvTypes:StateSignID>F77755F</ATvvTypes:StateSignID
		//ATvvTypes:StateSignID
		java.util.regex.Pattern patStateSignID = java.util.regex.Pattern.compile("ATvvTypes:StateSignID>.*</ATvvTypes:StateSignID");
		Matcher matchStateSignID = patStateSignID.matcher(newTemplate56);
		String newTemplate57 = matchStateSignID.replaceAll("ATvvTypes:StateSignID>"+generateStateSignID()+"</ATvvTypes:StateSignID");

		//OfftakeYear
		//ATvvTypes:OfftakeYear>.*</ATvvTypes:OfftakeYear
		java.util.regex.Pattern patOffTakeYear = java.util.regex.Pattern.compile("ATvvTypes:OfftakeYear>.*</ATvvTypes:OfftakeYear");
		Matcher matchOfftakeYear = patOffTakeYear.matcher(newTemplate57);
		String newTemplate58 = matchOfftakeYear.replaceAll("ATvvTypes:OfftakeYear>"+thisYear()+"</ATvvTypes:OfftakeYear");

		//IdentityDocNumber
		//ATrq8Types:IdentityDocNumber>112233</ATrq8Types:IdentityDocNumber
		java.util.regex.Pattern patIdDocNumber = java.util.regex.Pattern.compile("ATrq8Types:IdentityDocNumber>.*</ATrq8Types:IdentityDocNumber");
		Matcher matchIdDocNumber = patIdDocNumber.matcher(newTemplate58);
		String newTemplate59 = matchIdDocNumber.replaceAll("ATrq8Types:IdentityDocNumber>"+ generateIdentityDocNumber() +"</ATrq8Types:IdentityDocNumber");

		//IdentityDocDate
		//ATCompTypes:IdentityDocDate>2018-06-05T00:00:00+03:00</ATCompTypes:IdentityDocDate
		java.util.regex.Pattern patIdDocDate = java.util.regex.Pattern.compile("ATCompTypes:IdentityDocDate>.*</ATCompTypes:IdentityDocDate");
		Matcher matchIdDocDate = patIdDocDate.matcher(newTemplate59);
		String newTemplate60 = matchIdDocDate.replaceAll("ATCompTypes:IdentityDocDate>"+randomDate()+"T"+randTimeNow()+"</ATCompTypes:IdentityDocDate");

		//AddressLine
		//ATCompTypes:AddressLine>RU, asdf, sdaf</ATCompTypes:AddressLine
		java.util.regex.Pattern patAddressLine = java.util.regex.Pattern.compile("ATCompTypes:AddressLine>.*</ATCompTypes:AddressLine");
		Matcher matchAddressLine = patAddressLine.matcher(newTemplate60);
		String newTemplate61 = matchAddressLine.replaceAll("ATCompTypes:AddressLine>"+ generateRandomText()+"</ATCompTypes:AddressLine");


		//Surname
		//Surname>ASDF</ATCompTypes:Surname
		java.util.regex.Pattern patSurname = java.util.regex.Pattern.compile("Surname>.*</ATCompTypes:Surname");
		Matcher matchSurname = patSurname.matcher(newTemplate61);
		String newTemplate62 = matchSurname.replaceAll("Surname>"+arraySurnames[randSurname]+"</ATCompTypes:Surname");


		//Name
		//ATCompTypes:Name>ASFD</ATCompTypes:Name
		java.util.regex.Pattern patName = java.util.regex.Pattern.compile("ATCompTypes:Name>.*</ATCompTypes:Name");
		Matcher matchName = patName.matcher(newTemplate62);
		String newTemplate63 = matchName.replaceAll("ATCompTypes:Name>"+ arrayNames[randName]+"</ATCompTypes:Name");


		//BirthDay
		//ATCompTypes:BirthDay>1989-06-05T00:00:00+03:00</ATCompTypes:BirthDay
		java.util.regex.Pattern patBirthDate = java.util.regex.Pattern.compile("ATCompTypes:BirthDay>.*</ATCompTypes:BirthDay");
		Matcher matchBirthDay = patBirthDate.matcher(newTemplate63);
		String newTemplate64 = matchBirthDay.replaceAll("ATCompTypes:BirthDay>"+randomDate()+"T"+randTimeNow()+"</ATCompTypes:BirthDay");



		//BirthPlace
		//ATCompTypes:BirthPlace>gfdsafdsf</ATCompTypes:BirthPlace
		java.util.regex.Pattern patBirthPace = java.util.regex.Pattern.compile("ATCompTypes:BirthPlace>.*</ATCompTypes:BirthPlace");
		Matcher matchBirthPlace = patBirthPace.matcher(newTemplate64);
		String newTemplate65 = matchBirthPlace.replaceAll("ATCompTypes:BirthPlace>"+ randCity+"</ATCompTypes:BirthPlace");


		//StartDateTime>2017-12-06T16:14:08.4949414+03:00</q1:StartDateTime
		//StartDateTime
		java.util.regex.Pattern patStartDateTime = java.util.regex.Pattern.compile("StartDateTime>.*</q1:StartDateTime");
		Matcher matchStartDateTime = patStartDateTime.matcher(newTemplate65);
		String newTemplate66 = matchStartDateTime.replaceAll("StartDateTime>"+randomDate()+"T"+randTimeNow()+ "</q1:StartDateTime");



		//q1:UUID>04aca610-053d-11e1-99b4-d8d385fbc9e8</q1:UUID
		//UUID
		java.util.regex.Pattern patUUID = java.util.regex.Pattern.compile("q1:UUID>.*</q1:UUID");
		Matcher matchUUID = patUUID.matcher(newTemplate66);
		UUID uuid = UUID.randomUUID();
		String newTemplate67 = matchUUID.replaceAll("q1:UUID>"+uuid+"</q1:UUID");


		//PrDocumentName
		//cat_ru:PrDocumentName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">КОНОСАМЕНТ</cat_ru:PrDocumentName
		java.util.regex.Pattern patPrDocName = java.util.regex.Pattern.compile("cat_ru:PrDocumentName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PrDocumentName");
		Matcher matchPrDocName = patPrDocName.matcher(newTemplate67);
		String newTemplate68 = matchPrDocName.replaceAll("cat_ru:PrDocumentName xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+generateRandomText()+ "</cat_ru:PrDocumentName");



		//PrDocumentNumber
		//cat_ru:PrDocumentNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">FCFVLA16650</cat_ru:PrDocumentNumber
		java.util.regex.Pattern patPrDocNumb = java.util.regex.Pattern.compile("cat_ru:PrDocumentNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">.*</cat_ru:PrDocumentNumber");
		Matcher matchPrDocNumb = patPrDocNumb.matcher(newTemplate68);
		String newTemplate69 = matchPrDocNumb.replaceAll("cat_ru:PrDocumentNumber xmlns:cat_ru=\"urn:customs.ru:CommonAggregateTypes:5.10.0\">"+ generateRandomText()+"</cat_ru:PrDocumentNumber");



		//PreInfID>11111</PreInfID
		java.util.regex.Pattern patPreInfID = java.util.regex.Pattern.compile("PreInfID>.*</PreInfID");
		Matcher matchPreInfID = patPreInfID.matcher(newTemplate69);
		String newTemplate70 = matchPreInfID.replaceAll("PreInfID>"+ generatePreInfId() + "</PreInfID");

		//TNVEDCode>8703229098</TNVEDCode
		java.util.regex.Pattern patTnved = java.util.regex.Pattern.compile("TNVEDCode>.*</TNVEDCode");
		Matcher matchTnved = patTnved.matcher(newTemplate70);
		String newTemplate71 = matchTnved.replaceAll("TNVEDCode>"+ random11Length() + "</TNVEDCode");



		//Model>NISSAN NOTE</Model
		java.util.regex.Pattern patModel = java.util.regex.Pattern.compile("Model>.*</Model");
		Matcher maatchModel = patModel.matcher(newTemplate71);
		//String



		return newTemplate71;
	}

	public String random11Length() {
		Random rnd = new Random();
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
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+""+num9+""+num10+""+num11;
		return s;
	}

	public void chooserTypeXML(String xml) {
		if (xml.contains("Cnfrm")) {		//1
			typeXml= "Cnfrm";
		}

		if (xml.contains("<edcnt:ED_Container")) {		//2 edcnt:ED_Container
			typeXml= "ED_Container";
		}
		if (xml.contains("<Result")) {		//3
			typeXml = "Result";
		}
		if (xml.contains("<VVinq")) {		//4
			typeXml= "VVinq";
		}
		if (xml.contains("VVdrvrInq")) {	//5
			typeXml= "VVdrvrInq";
		}
		if (xml.contains("VVinfAlone")) {		//6
			typeXml= "VVinfAlone";
		}
		if (xml.contains("FOIVRequest")) {		//7
			typeXml= "FOIVRequest";
		}
		if (xml.contains("<Notif_PIResult>")) {		//8
			typeXml= "Notif_PIResult";
		}

		if (xml.contains("<LotArrival>")) {		//9
			typeXml= "LotArrival";
		}

		if (xml.contains("ATVVinf:VVinf")) {	//10
			typeXml = "ATVVinf:VVinf";
		}

		if (xml.contains("ResultTKOUT")) {	//11
			typeXml = "ResultTKOUT";
		}
		if (xml.contains("<LotDeparture>")) {		//12
			typeXml= "LotDeparture";
		}
		if (xml.contains("<LotTrainDeparture>")) {		//13
			typeXml= "LotTrainDeparture";
		}
		//no 14 xml
		if (xml.contains("ReqInventoryDoc")) {		//14
			typeXml= "ReqInventoryDoc";
		}
		//without 15 xml
		if (xml.contains("RevealedRisks")) {		//15
			typeXml= "RevealedRisks";
		}
		//without 16 xml
		if (xml.contains("RegistrationComplete")) {		//16
			typeXml= "RegistrationComplete";
		}
		if (xml.contains("<InqInformDocum>")) {		//17
			typeXml= "InqInformDocum";
		}
		if (xml.contains("InspectionReqResFITO")) {		//18
			typeXml= "InspectionReqResFITO";
		}
		if (xml.contains("<TransportArrival>")) {		//19
			typeXml= "TransportArrival";
		}
		if (xml.contains("<LotAircraftArrival>")) {		//20
			typeXml= "LotAircraftArrival";
		}

	}

	public String generatorVIN() {
		final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int STR_LENGTH = 9;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);
			builder.append(ch);
		}
		Random rnd = new Random();
		int num1, num2, num3;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		String s = builder.toString() + ""+num1+""+num2+""+num3;

		return s;
	}

	public LocalDate randomDate() {
		return LocalDate.now().minus(Period.ofDays(new Random().nextInt(365*70)));
	}

	public String randomOGRN() {
		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14;

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
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+""+num9+""+num10+""+num11+""+num12+""+num13+""+num14;
		return s;
	}

	public String randomINN() {
		Random rnd = new Random();
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
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+""+num9+""+num10+""+num11;
		return s;
	}

	public String randomKPP() {
		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
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
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+""+num9+""+num10+"";
		return s;
	}

	public String randomOfficeName() {
		String arrayOfficeName[] = {"Фресно","Фуллертон","Хавторн","Хагсон","Хантингтон-Бич","Хантингтон-Парк","Хебер","Хейвард","Холтвилл","Хоумстид-Вэлли","Церес","Цитрус-Хейгтс","Черриленд","Чино","Чула-Виста","Эль-Кайон","Эль-Монт","Эль-Сегундо","Эль-Серрито","Эмеривилл","Эскалон","Эскондидо","Эурека","Юба-Сити","Андовер","Бентон","Вествуд","Вествуд-Хиллс","Винфилд","Вичита","Вэлли-Сентер","Грейт-Бенд","Джанкшин-Сити","Додж-Сити","Индепенденс","Истборо","Канзас-Сити","Кантрисайд","Карбондал","Кечи","Колби","Колвич","Конкордиа","Ларнед","Лекомптон","Ливенворт","Лоуренс","Манхаттан","Мерриам","Миссион","Миссион-Вудс","Миссион-Хиллс","Нортон","Оакли","Обурн","Овербрук","Оверленд-Парк","Огден","Палмер","Парк-Сити","Парсонс","Перри","Прейри-Виллидж","Роланд-Парк","Рос-Хилл","Салина","Силвер-Лейк","Скрантон","Топика","Файрвэй","Форт-Райли","Фронтенак","Хатчинсон","Хэйс","Чанут","Эмпориа","Адубон-Парк","Ашланд","Баулинг Грин","Беллевуэ","Беллефонт","Валлинс-Крик","Версаиллес","Вествуд","Вилмор","Вэйланд","Гутри","Джорджтаун","Дэйтон","Еминенк","Катлеттсбург","Кентон-Вейл","Кингсли","Ковингтон","Ла Фэйетт","Лексингтон","Линнвив","Лондон","Лоуисвилл","Лудлау","Маунт-Стерлинг","Николасвиль","Ньюпорт","Овенсборо","Олбани","Падуках","Парис","Парк-Хиллс","Парквэй-Виллидж","Певи Валли","Пемброк","Ракеланд","Русселл","Саут-Шор","Саутгейт","Сенека-Гарденс","Стампинг-Граунд","Стратмур-Гарденс","Стратмур-Манор","Трентон","Флатвудс","Форт Кампбелл Норт","Форт-Вригт","Форт-Митчелл","Форт-Нокс","Форт-Томас","Франкфорт","Хиден","Хопкинсвилл","Хорс-Кейв","Шивели","Айдахо-Спрингс","Арвада","Аурора","Блэк-Форест","Боулдер","Браш","Велби","Вестминстер","Вет-Ридж","Вудленд-Парк","Гленвуд-Спрингс","Гранд-Джанкшин","Грили","Грин-Маунтайн-Фоллс","Гринвуд-Виллидж","Денвер","Дерби","Диллон","Дуранго","Инглевуд","Колорадо-Спрингс","Коммерц-Сити","Крайг","Лас-Анимас","Лейквуд","Лейксайд","Литтлетон","Манитоу-Спрингс","Нанн","Нортгленн","Пуэбло","Саутгленн","Свинк","Секьюрити","Форт-Карсон","Форт-Коллинс","Черри-Хиллс-Виллидж","Шеридан","Шеррелвуд","Эджуотер","Эйр-Форс-Академи","Берлин","Бриджпорт","Бристоль","Валлингфорд","Вернон","Вест-Хавен","Вест-Хартфорд","Вестпорт","Ветерсфилд","Гластонбури","Гринвич","Данбури","Ист-Хавен","Ист-Хартфорд","Кенсингтон","Куинбаг","Манчестер","Мериден","Миддлетаун","Милфорд","Невингтон","Норвич","Норволк","Норт-Гросвенор-Дейл","Нью-Бритайн","Нью-Лондон","Нью-Хейвен","Патнам","Роки-Хилл","Стамфорд","Стратфорд","Торрингтон","Трамбалл","Уотербури","Файрфилд","Фармингтон","Хамден","Хартфорд","Шелтон","Абита-Спрингс","Александрия","Анги","Балл","Батон-Руж","Бейкер","Бентон","Бланчард","Богалуса","Боссир-Сити","Варнадо","Велш","Видалиа","Вильсон","Вудворт","Гонзалес","Грамблинг","Де-Риддер","Денхам-Спрингс","Джексон","Дусон","Зачари","Канктон","Карвилл","Кеннер","Клейтон","Ковингтон","Коттон-Вэлли","Краули","Куллен","Лафайетт","Лейк-Чарльз","Лекомпт","Лисвилл","Марреро","Мерривилл","Метаири","Монро","Морган-Сити","Мосс-Блуфф","Новый Орлеан","Нью-Ибериа","Олбани","Пайнвилл","Пирл Ривер","Порт-Аллен","Рэйн","Сансет","Сарепта","Скотландвилл","Скотт","Слаутер","Спрингилл","Стоунволл"};
		int officeLength = arrayOfficeName.length;
		int randOffice = (int) (Math.random()*officeLength);
		String s = "т/п Морской порт " + arrayOfficeName[randOffice];
		return s;
	}

	public String randomWorknodes() {
		String arrayWorknodes[] = {"100000001","1000000041","10000000ASAT","10000000AUTOTR","10000000AUTOTR2","10000000AUTOTRQ","10000000AUTOTRQ2","10000000EPS","10000000EXPERTDB","10000000FGTD","10000000GNIVC","10000000MBGNIVC","10000000PIGNIVC","10000000PIGNIVC_TEST","10000000SCVV","10000000SUR","10000000TIR","10000000VZAIMIS","1000203042","1000203043","10002030SRV_GD","101000001","1010000041","10100000ACKTT","10100000AISTM","10100000BDRD","10100000IDK","10100000RTU1","10100000SCVV","10100000SUR","10101010101","10101010121","10101010141","10101010142",
				"101010102","1010101021","1010101022","10101010221","1010101023","1010101024","1010101025","1010101026","1010101027","10101010281","101010103","10101010301","10101010341","10101010402","1010101061","1010101081","1010101082","10101010SRV_AVIA","10101010SRV_AVTO","10101010SRV_GD","10101010SRV_SEA","10101020161","10101020162","10101020321","10101020362","10101020401","10101020SRV_AVTO/UA0044","10101030181","10101030182","10101030201","10101030241","10101030261","10101030361","10101030381","10101030SRV_AVIA","10200000RTU2","10300000RTU3","10400000RTU4","10500000RTU5","10600000RTU6","10700000RTU7","10700000RTU8"};
		int worknodesLength = arrayWorknodes.length;
		int randworknodes = (int) (Math.random()*worknodesLength);
		String randworknod = arrayWorknodes[randworknodes];
		return randworknod;
	}

	public String randomcustomCodes() {
		String arrayCustomCodes[] = {"10000000","10001000","10001010","10001020","10002000","10002001","10002002",
				"10002010","10002011","10002012","10002020","10002030","10002040","10003000","10003010","10004000",
				"10004001","10004002","10004003","10005000","10005001","10005002","10005003","10005004","10005005",
				"10005006","10005007","10005008","10005010","10005020","10005021"};
		int custcodeLength  = arrayCustomCodes.length;
		int randcustcod = (int) (Math.random()*custcodeLength);
		String randcustcodes = arrayCustomCodes[randcustcod];
		return randcustcodes;
	}

	public String randomGrossWeight() {

		double max = 1000.0;
		double weightRandom = Math.random()*max;
		String formattedWeight = String.format("%.1f", weightRandom);
		return formattedWeight;
	}

	public String randPrDate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(2018, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2018, 6, 30).toEpochDay();
		//int time1 = (int) LocalTime.of(00, 00).toString();
		long randomDay = minDay + random.nextInt(maxDay-minDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		String ranDate = randomDate.toString();
		return ranDate;
	}


	public String generateContainerIdentificaror() {
		final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int STR_LENGTH = 4;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);
			builder.append(ch);
		}
		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6, num7;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		num7 = rnd.nextInt(10);
		String s = builder.toString() + ""+num1+""+num2+""+num3+""+num4+""+num5+""+num6+""+num7;
		return s;
	}

	public String generateIdentCard() {
		Random rnd = new Random();
		int num1, num2;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		String s = num1+""+num2;
		return s;
	}

	public StringBuilder generatorPatIdentCardName() {
		final String CHARS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ";
		final int STR_LENGTH = 5;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);
			builder.append(ch);
		}
		return builder;
	}

	public String generateSeries() {
		Random rnd = new Random();
		int num1, num2, num3, num4;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		String s = num1+""+num2+" "+num3 + ""+num4;
		return s;
	}

	public String generateNumber() {
		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		String s = num1+""+num2+""+num3 + ""+num4+""+num5+""+num6;
		return s;
	}

	public StringBuilder generateRandomText() {
		final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int STR_LENGTH = 15;
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<STR_LENGTH; i++) {
			int number = random.nextInt(CHARS.length());
			char ch = CHARS.charAt(number);
			builder.append(ch);
		}
		return builder;
	}

	public String randomGoodsQuan() {

		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6, num7;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		num7 = rnd.nextInt(10);
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7;
		return s;
	}

	public String randomMeasUnQualCode() {

		Random rnd = new Random();
		int num1, num2, num3;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);

		String s = num1+""+ num2+""+num3;
		return s;
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
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6+""+num7+""+num8+"-"+num9+""+num10+""+num11+"-"+num12+""+num13+""+num14+""+num15+""+num16+""+num17+"-"+num18+""+num19+""+num20+""+num21+"-"+num22;
		return s;
	}

	public String randDateNow() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		//SimpleDateFormat formatForDb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String date = simpleFormat.format(new Date());
		return date;
	}

	public String randTimeNow() {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String time = sf.format(date.getTime());
		return time;
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

	public String generateStateSignID() {
		//F77755F
		int num1, num2, num3, num4, num5;
		Random rnd = new Random();
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		String s = RandomStringUtils.randomAlphabetic(1)+ num1+""+num2+""+num3+""+num4+""+num5+RandomStringUtils.randomAlphabetic(1);
		return s;
	}

	public String thisYear() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		String date = sf.format(new Date());
		return date;
	}

	public String generateIdentityDocNumber() {
		Random rnd = new Random();
		int num1, num2, num3, num4, num5, num6;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		num6 = rnd.nextInt(10);
		String s = num1+""+ num2+""+num3+""+num4+""+num5+""+num6;
		return s;
	}

	public String generatePreInfId() {
		Random rnd = new Random();
		int num1, num2, num3, num4, num5;
		num1 = rnd.nextInt(10);
		num2 = rnd.nextInt(10);
		num3 = rnd.nextInt(10);
		num4 = rnd.nextInt(10);
		num5 = rnd.nextInt(10);
		String s = num1+""+ num2+""+num3+""+num4+""+num5;
		return s;
	}



}



