package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

public class ConstantList {

	// List for keywords
		public List<String> getKeywords() {
			List<String> list = new ArrayList<>();
			list.add("Introduction");
			list.add("General");
			list.add("Punishment");
			list.add("Exception");
			list.add("Private Defence");
			list.add("Abetment");
			list.add("Conspiracy");
			list.add("Offence Against the State");
			list.add("Offence Realated to Army");
			list.add("Offence Against Public Transquillity");
			list.add("Public Servant");
			list.add("Election");
			list.add("Contempt of Lawful Athority");
			list.add("Offence Against Public Justice");
			list.add("Coins");
			list.add("Stamp");
			list.add("Weights and Measures");
			list.add("Offence Affecting Public Safety");
			list.add("Religion");
			list.add("Affecting Life");
			list.add("Miscarriage");
			list.add("Hurt");
			list.add("Wrongful Confinement");
			list.add("Criminal Force and Assault");
			list.add("Kidnapping");
			list.add("Slaves");
			list.add("Extortion");
			list.add("Robbery and Dacoity");
			list.add("Of Criminal Misappropriation of Property");
			list.add("Of Criminal Breach of Trust");
			list.add("Of the Receiving of Stolen Property");
			list.add("Of Cheating");
			list.add("Of Fraudulent Deeds and Dispositions of Property");
			list.add("Of Mischief");
			list.add("Of Criminal Trespass");
			list.add("OF OFFENCES RELATING TO DOCUMENTS AND TO PROPERTY MARKS");
			list.add("Of Property and Other Marks");
			list.add("Of Currency-Notes and Bank-Notes");
			list.add("OF THE CRIMINAL BREACH OF CONTRACTS OF SERVICE");
			list.add("OF OFFENCES RELATING TO MARRIAGE");
			list.add("OF CRUELTY BY HUSBAND OR RELATIVES OF HUSBAND");
			list.add("OF DEFAMATION");
			list.add("OR CRIMINAL INTIMIDATION , INSULTAND ANNOYANCE");
			list.add("OF ATTEMPTS OF COMMIT OFFENCES");
			return list;
		}

		// List of types of court
		public List<String> getCourt() {
			List<String> lists = new ArrayList<>();
			lists.add("Supreme");
			lists.add("High");
			lists.add("District");
			return lists;
		}

		// List of types of lawyers
		public List<String> getType() {
			List<String> list = new ArrayList<>();
			list.add("Criminal");
			list.add("Corporat");
			list.add("Tax");
			list.add("RealEstate");
			list.add("Immigration");
			list.add("Personal Injury");
			return list;
		}

		// List of types of cases
		public List<String> getCaseType() {
			List<String> list = new ArrayList<>();
			list.add("Criminal");
			list.add("Corporat");
			list.add("Tax");
			list.add("RealEstate");
			list.add("Immigration");
			list.add("Personal Injury");
			return list;
		}
		

		//List for types of roles
		public List<String> getRoles() {
			List<String> list = new ArrayList<>();
			list.add("ADMIN");
			list.add("USER");
			list.add("LAWYER");
			return list;
		}
}
