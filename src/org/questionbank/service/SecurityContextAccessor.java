package org.questionbank.service;

public interface SecurityContextAccessor {

	boolean isCurrentAuthenticationAnonymous();

	String determineDefaultTargetUrl();
}