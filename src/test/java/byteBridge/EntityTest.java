package byteBridge;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import byteBridge.Entity.Entities;
import byteBridge.Page.Permissions;

class EntityTest
{
	ByteBridgeState byteBridge;
	Person C;
	Person T;
	Person L;
	Person RESTTest;
	Page CPage;
	Page TPage;
	Page LPage;
	Page TestPage;
	Company CC;
	Company BP;
	Company LF;
	Page CCPage;
	Page BPPage;
	Page LFPage;
	JobPosting CCJob;
	JobPosting BPJob;
	JobPosting LFJob;
	Page CCJobPage;
	Page BPJobPage;
	Page LFJobPage;
	Project VE;
	Project Bombe;
	Project LK;
	Page VEPage;
	Page BombePage;
	Page LKPage;
	Skill Cello;
	Skill SoftwareDev;
	Skill Cryptography;
	News CNews;
	News TNews;
	News LNews;
	String temp;
	ServerHandler server;
	
	@BeforeEach
	void setUp() throws Exception
	{
		byteBridge = ByteBridgeState.getInstance();
		
		CPage = new Page("Cooper Hendrix's Profile", null);
		C = new Person("1", "Cooper Hendrix", CPage, "Student");
		TPage = new Page("Alan Turing's Profile", null);
		T = new Person("2", "Alan Turing", TPage, "Theoretical Computer Scientist");
		LPage = new Page("Linus Torvalds' Profile", null);
		L = new Person("3", "Linus Torvalds", LPage, "Linux Kernel Developer");
		
		CCPage = new Page("Centre College's Profile", null);
		CC = new Company("1", "Centre College", CCPage);
		BPPage = new Page("Bletchley Park's Profile", null);
		BP = new Company("2", "Bletchley Park", BPPage);
		LFPage = new Page("Linux Foundation's Profile", null);
		LF = new Company("3", "Linux Foundation", LFPage);
		
		CCJobPage = new Page("Centre College's Job Posting", null);
		CCJob = new JobPosting("1", "Student Programmer", CCJobPage);
		BPJobPage = new Page("Bletchley Park's Job Posting", null);
		BPJob = new JobPosting("2", "Codebreaker", BPJobPage);
		LFJobPage = new Page("Linux Foundation's Job Posting", null);
		LFJob = new JobPosting("3", "Linux Kernel Developer", LFJobPage);
		
		VEPage = new Page("Virtual Exhibition Project", null);
		VE = new Project("1", "Virtual Exhibition", VEPage);
		BombePage = new Page("Bombe Enigma Decoder Project", null);
		Bombe = new Project("2", "Bombe Enigma Decoder", BombePage);
		LKPage = new Page("Linux Kernel Project", null);
		LK = new Project("3", "Linux Kernel", LKPage);
		
		/// These nulls will have pages, but there isn't a point in testing it here
		Cello = new Skill("1", "Cello Performance", null);
		SoftwareDev = new Skill("2", "Software Development", null);
		Cryptography = new Skill("3", "Cryptography", null);
		
		CNews = new News("1", "Performed At Norton Center", null);
		TNews = new News("2", "Cracked Enigma Code", null);
		LNews = new News("3", "Made Linux Kernel", null);
		
		TestPage = new Page("Test Page", null);
		RESTTest = new Person("4", "Person", TestPage, "Occupation");
		
		server = new ServerHandler();
		
	}

	@Test
	void test() throws Exception
	{
		//// Profile Tests ////
		assertEquals(C.name, "Cooper Hendrix");
		assertEquals(C.page.description, "Cooper Hendrix's Profile");
		C.setEmployer(CC);
		String Cemployer = C.links.get(Entities.EMPLOYER).get(0);
		assertEquals(Cemployer, "1");
		C.followCompany(BP);
		String Cfollow = C.links.get(Entities.FOLLOWING).get(0);
		assertEquals(Cfollow, "2");
		C.followPerson(L);
		Cfollow = C.links.get(Entities.FOLLOWING).get(1);
		assertEquals(Cfollow, "3");
		
		
		assertEquals(T.name, "Alan Turing");
		assertEquals(T.page.description, "Alan Turing's Profile");
		T.setEmployer(BP);
		String Temployer = T.links.get(Entities.EMPLOYER).get(0);
		assertEquals(Temployer, "2");
		
		assertEquals(L.name, "Linus Torvalds");
		assertEquals(L.page.description, "Linus Torvalds' Profile");
		L.setEmployer(LF);
		String Lemployer = L.links.get(Entities.EMPLOYER).get(0);
		assertEquals(Lemployer, "3");
		
		//// Company Tests ////
		CC.addEmployee(C);
		String CCemployee = CC.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(CCemployee, "1");
		CC.removeEmployee(C);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = CC.links.get(Entities.EMPLOYEES).get(0);
				});
		CC.addProject(VE);
		String CCproject = CC.links.get(Entities.PROJECT).get(0);
		assertEquals(CCproject, "1");
		CC.removeProject(VE);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = CC.links.get(Entities.PROJECT).get(0);
				});
		
		BP.addEmployee(T);
		String BPemployee = BP.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(BPemployee, "2");
		BP.removeEmployee(T);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = BP.links.get(Entities.EMPLOYEES).get(0);
				});
		BP.addProject(Bombe);
		String BPproject = BP.links.get(Entities.PROJECT).get(0);
		assertEquals(BPproject, "2");
		BP.removeProject(Bombe);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = BP.links.get(Entities.PROJECT).get(0);
				});
		
		LF.addEmployee(L);
		String LFemployee = LF.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(LFemployee, "3");
		LF.removeEmployee(L);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = LF.links.get(Entities.EMPLOYEES).get(0);
				});
		LF.addProject(LK);
		String LFproject = LF.links.get(Entities.PROJECT).get(0);
		assertEquals(LFproject, "3");
		LF.removeProject(LK);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = LF.links.get(Entities.PROJECT).get(0);
				});
		
		//// Post Tests ////
		C.post(CNews);
		String CNewsName = C.links.get(Entities.NEWS).get(0);
		assertEquals(CNewsName, "1");
		
		T.post(TNews);
		String TNewsName = T.links.get(Entities.NEWS).get(0);
		assertEquals(TNewsName, "2");
		
		L.post(LNews);
		String LNewsName = L.links.get(Entities.NEWS).get(0);
		assertEquals(LNewsName, "3");
		
		//// Skills Tests ////
		C.addSkill(Cello);
		String Cskill = C.links.get(Entities.SKILL).get(0);
		assertEquals(Cskill, "1");
		C.removeSkill(Cello);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = C.links.get(Entities.SKILL).get(0);
				});
		
		T.addSkill(Cryptography);
		String Tskill = T.links.get(Entities.SKILL).get(0);
		assertEquals(Tskill, "3");
		T.removeSkill(Cryptography);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = T.links.get(Entities.SKILL).get(0);
				});
		
		L.addSkill(SoftwareDev);
		String Lskill = L.links.get(Entities.SKILL).get(0);
		assertEquals(Lskill, "2");
		L.removeSkill(SoftwareDev);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					temp = L.links.get(Entities.SKILL).get(0);
				});
		
		//// Projects Tests ////
		C.addProject(VE);
		String Cproject = C.links.get(Entities.PROJECT).get(0);
		assertEquals(Cproject, "1");
		C.removeProject(VE);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = C.links.get(Entities.PROJECT).get(0);
				});
		
		T.addProject(Bombe);
		String Tproject = T.links.get(Entities.PROJECT).get(0);
		assertEquals(Tproject, "2");
		T.removeProject(Bombe);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = T.links.get(Entities.PROJECT).get(0);
				});
		
		L.addProject(LK);
		String Lproject = L.links.get(Entities.PROJECT).get(0);
		assertEquals(Lproject, "3");
		L.removeProject(LK);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = L.links.get(Entities.PROJECT).get(0);
				});
		
		//// Apply & Job Posting Tests ////
		CC.addJobPosting(CCJob);
		String CCjobname = CC.links.get(Entities.JOBPOSTING).get(0);
		assertEquals(CCjobname, "1");
		C.apply(CCJob);
		CCjobname = CCJob.links.get(Entities.APPLICANTS).get(0);
		assertEquals(CCjobname, "1");
		CC.addEmployee(C);
		CCjobname = CC.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(CCjobname, "1");
		CC.removeJobPosting(CCJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = CC.links.get(Entities.JOBPOSTING).get(0);
				});
		
		BP.addJobPosting(BPJob);
		String BPjobname = BP.links.get(Entities.JOBPOSTING).get(0);
		assertEquals(BPjobname, "2");
		T.apply(BPJob);
		BPjobname = BPJob.links.get(Entities.APPLICANTS).get(0);
		assertEquals(BPjobname, "2");
		BP.addEmployee(T);
		BPjobname = BP.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(BPjobname, "2");
		BP.removeJobPosting(BPJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = BP.links.get(Entities.JOBPOSTING).get(0);
				});
		
		LF.addJobPosting(LFJob);
		String LFjobname = LF.links.get(Entities.JOBPOSTING).get(0);
		assertEquals(LFjobname, "3");
		L.apply(LFJob);
		LFjobname = LFJob.links.get(Entities.APPLICANTS).get(0);
		assertEquals(LFjobname, "3");
		LF.addEmployee(L);
		LFjobname = LF.links.get(Entities.EMPLOYEES).get(0);
		assertEquals(LFjobname, "3");
		LF.removeJobPosting(LFJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = LF.links.get(Entities.JOBPOSTING).get(0);
				});
		
		//// Page Permissions Testing ////
		CPage.addPermission(C);
		String CPagePermission = CPage.permissions.get(Permissions.WRITE).get(0);
		assertEquals(CPagePermission, "1");
		assertEquals(CPage.hasPermission(C), true);
		CPage.removePermission(C);
		assertEquals(CPage.hasPermission(C), false);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = CPage.permissions.get(Permissions.WRITE).get(0);
				});
		
		
		//// REST Testing ////
		String storeconfirm = C.storeData(server.client);
		storeconfirm = C.storeData(server.client);
		System.out.println(storeconfirm);
		storeconfirm = CC.storeData(server.client);
		System.out.println(storeconfirm);
		storeconfirm = CCJob.storeData(server.client);
		System.out.println(storeconfirm);
		storeconfirm = CNews.storeData(server.client);
		System.out.println(storeconfirm);
		storeconfirm = VE.storeData(server.client);
		System.out.println(storeconfirm);
		storeconfirm = Cello.storeData(server.client);
		System.out.println(storeconfirm);
		
		C.retrieveData(server.client);
		C.setId("15");
		C.updateData(server.client);
		C.setId("1");
		C.updateData(server.client);
		
		CC.retrieveData(server.client);
		CC.setId("15");
		CC.updateData(server.client);
		CC.setId("1");
		CC.updateData(server.client);
		
		CCJob.retrieveData(server.client);
		CCJob.setId("15");
		CCJob.updateData(server.client);
		CCJob.setId("1");
		CCJob.updateData(server.client);
		
		CNews.retrieveData(server.client);
		CNews.setId("15");
		CNews.updateData(server.client);
		CNews.setId("1");
		CNews.updateData(server.client);
		
		VE.retrieveData(server.client);
		VE.setId("15");
		VE.updateData(server.client);
		VE.setId("1");
		VE.updateData(server.client);
		
		Cello.retrieveData(server.client);
		Cello.setId("15");
		Cello.updateData(server.client);
		Cello.setId("1");
		Cello.updateData(server.client);
		
		
		//// Job Recommender Testing ////
		CCJob.recommender.setEveryone(true);
		CCJob.recommender.addRecommendations();
		ArrayList<String> CCJobRecommends = CCJob.recommender.recommended;
		assertEquals("1", CCJobRecommends.get(0));
		assertEquals("2", CCJobRecommends.get(1));
		assertEquals("3", CCJobRecommends.get(2));
		
		BPJob.addSkills(Cryptography.id);
		T.addSkill(Cryptography);
		BPJob.recommender.setSkills(true);
		BPJob.recommender.setEveryone(false);
		BPJob.recommender.addRecommendations();
		ArrayList<String> BPJobRecommends = BPJob.recommender.recommended;
		assertEquals("2", BPJobRecommends.get(0));
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = BPJobRecommends.get(1);
				});
		
		LFJob.recommender.setEveryone(false);
		LFJob.recommender.setTitle(true);
		LFJob.recommender.addRecommendations();
		ArrayList<String> LFJobRecommends = LFJob.recommender.recommended;
		assertEquals("3", LFJobRecommends.get(0));
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					temp = LFJobRecommends.get(1);
				});
	}

}
