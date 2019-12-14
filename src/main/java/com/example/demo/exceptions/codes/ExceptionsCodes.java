package com.example.demo.exceptions.codes;

public interface ExceptionsCodes {
	//USUARIS
	final String USE_NOT_FOUND = "EMUSE01";
	final String USE_EMPTY_RESULT = "EMUSE02";
	final String USE_SYSTEM_NOT_VALID = "EMUSE03";
	
	//OBSTACLES

	final String OBS_NOT_FOUND = "EMOBS01";
	final String OBS_DUPLICATE = "EMOBS02";
	final String OBS_EMPTY_RESULT = "EMOBS03";
	final String OBS_ALREADY_VOTED ="EMOBS04";

	
	//RESET TOKEN
	final String TOK_NOT_FOUND = "EMTOK01";
	final String TOK_EXPIRED = "EMTOK02";
	
}
