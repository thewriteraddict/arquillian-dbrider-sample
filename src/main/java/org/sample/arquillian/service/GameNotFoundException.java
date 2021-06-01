package org.sample.arquillian.service;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class GameNotFoundException extends RuntimeException {

}
