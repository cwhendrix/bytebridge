package byteBridge;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import byteBridge.Entity.Entities;

class EntityTest
{
	Person C;
	Person T;
	Person L;
	Page CPage;
	Page TPage;
	Page LPage;
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
	
	@BeforeEach
	void setUp() throws Exception
	{
		CPage = new Page("Cooper Hendrix's Profile", null);
		C = new Person("1", "Cooper Hendrix", CPage, "Student");
		TPage = new Page("Alan Turing's Profile", null);
		T = new Person("2", "Alan Turing", TPage, "Theoretical Computer Scientist");
		LPage = new Page("Linus Torvalds' Profile", null);
		L = new Person("3", "Linus Torvalds", LPage, "Software Engineer");
		
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
		
	}

	@Test
	void test()
	{
		//// Profile Tests ////
		assertEquals(C.name, "Cooper Hendrix");
		assertEquals(C.page.description, "Cooper Hendrix's Profile");
		C.setEmployer(CC);
		String Cemployer = C.links.get(Entities.EMPLOYER).get(0).name;
		assertEquals(Cemployer, "Centre College");
		C.followCompany(LF);
		String Cfollow = C.links.get(Entities.FOLLOWING).get(0).name;
		assertEquals(Cfollow, "Linux Foundation");
		C.followPerson(L);
		Cfollow = C.links.get(Entities.FOLLOWING).get(1).name;
		assertEquals(Cfollow, "Linus Torvalds");
		
		assertEquals(T.name, "Alan Turing");
		assertEquals(T.page.description, "Alan Turing's Profile");
		T.setEmployer(BP);
		String Temployer = T.links.get(Entities.EMPLOYER).get(0).name;
		assertEquals(Temployer, "Bletchley Park");
		
		assertEquals(L.name, "Linus Torvalds");
		assertEquals(L.page.description, "Linus Torvalds' Profile");
		L.setEmployer(LF);
		String Lemployer = L.links.get(Entities.EMPLOYER).get(0).name;
		assertEquals(Lemployer, "Linux Foundation");
		
		//// Post Tests ////
		C.post(CNews);
		String CNewsName = C.links.get(Entities.NEWS).get(0).name;
		assertEquals(CNewsName, "Performed At Norton Center");
		
		T.post(TNews);
		String TNewsName = T.links.get(Entities.NEWS).get(0).name;
		assertEquals(TNewsName, "Cracked Enigma Code");
		
		L.post(LNews);
		String LNewsName = L.links.get(Entities.NEWS).get(0).name;
		assertEquals(LNewsName, "Made Linux Kernel");
		
		//// Skills Tests ////
		C.addSkill(Cello);
		String Cskill = C.links.get(Entities.SKILL).get(0).name;
		assertEquals(Cskill, "Cello Performance");
		C.removeSkill(Cello);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					String temp = C.links.get(Entities.SKILL).get(0).name;
				});
		
		T.addSkill(Cryptography);
		String Tskill = T.links.get(Entities.SKILL).get(0).name;
		assertEquals(Tskill, "Cryptography");
		T.removeSkill(Cryptography);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					String temp = T.links.get(Entities.SKILL).get(0).name;
				});
		
		L.addSkill(SoftwareDev);
		String Lskill = L.links.get(Entities.SKILL).get(0).name;
		assertEquals(Lskill, "Software Development");
		L.removeSkill(SoftwareDev);
		assertThrows(IndexOutOfBoundsException.class, 
				()-> {
					String temp = L.links.get(Entities.SKILL).get(0).name;
				});
		
		//// Projects Tests ////
		C.addProject(VE);
		String Cproject = C.links.get(Entities.PROJECT).get(0).name;
		assertEquals(Cproject, "Virtual Exhibition");
		Cproject = C.links.get(Entities.PROJECT).get(0).page.description;
		assertEquals(Cproject, "Virtual Exhibition Project");
		C.removeProject(VE);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = C.links.get(Entities.PROJECT).get(0).name;
				});
		
		T.addProject(Bombe);
		String Tproject = T.links.get(Entities.PROJECT).get(0).name;
		assertEquals(Tproject, "Bombe Enigma Decoder");
		Tproject = T.links.get(Entities.PROJECT).get(0).page.description;
		assertEquals(Tproject, "Bombe Enigma Decoder Project");
		T.removeProject(Bombe);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = T.links.get(Entities.PROJECT).get(0).name;
				});
		
		L.addProject(LK);
		String Lproject = L.links.get(Entities.PROJECT).get(0).name;
		assertEquals(Lproject, "Linux Kernel");
		Lproject = L.links.get(Entities.PROJECT).get(0).page.description;
		assertEquals(Lproject, "Linux Kernel Project");
		L.removeProject(LK);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = L.links.get(Entities.PROJECT).get(0).name;
				});
		
		//// Apply & Job Posting Tests ////
		CC.addJobPosting(CCJob);
		String CCjobname = CC.links.get(Entities.JOBPOSTING).get(0).name;
		assertEquals(CCjobname, "Student Programmer");
		C.apply(CCJob);
		CCjobname = CCJob.links.get(Entities.APPLICANTS).get(0).name;
		assertEquals(CCjobname, "Cooper Hendrix");
		CC.addEmployee(C);
		CCjobname = CC.links.get(Entities.EMPLOYEES).get(0).name;
		assertEquals(CCjobname, "Cooper Hendrix");
		CC.removeJobPosting(CCJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = CC.links.get(Entities.JOBPOSTING).get(0).name;
				});
		
		BP.addJobPosting(BPJob);
		String BPjobname = BP.links.get(Entities.JOBPOSTING).get(0).name;
		assertEquals(BPjobname, "Codebreaker");
		T.apply(BPJob);
		BPjobname = BPJob.links.get(Entities.APPLICANTS).get(0).name;
		assertEquals(BPjobname, "Alan Turing");
		BP.addEmployee(T);
		BPjobname = BP.links.get(Entities.EMPLOYEES).get(0).name;
		assertEquals(BPjobname, "Alan Turing");
		BP.removeJobPosting(BPJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = BP.links.get(Entities.JOBPOSTING).get(0).name;
				});
		
		LF.addJobPosting(LFJob);
		String LFjobname = LF.links.get(Entities.JOBPOSTING).get(0).name;
		assertEquals(LFjobname, "Linux Kernel Developer");
		L.apply(LFJob);
		LFjobname = LFJob.links.get(Entities.APPLICANTS).get(0).name;
		assertEquals(LFjobname, "Linus Torvalds");
		LF.addEmployee(L);
		LFjobname = LF.links.get(Entities.EMPLOYEES).get(0).name;
		assertEquals(LFjobname, "Linus Torvalds");
		LF.removeJobPosting(LFJob);
		assertThrows(IndexOutOfBoundsException.class,
				()-> {
					String temp = LF.links.get(Entities.JOBPOSTING).get(0).name;
				});
		
	}

}
