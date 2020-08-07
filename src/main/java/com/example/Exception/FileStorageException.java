package com.example.Exception;

public class FileStorageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4194344111217454053L;
	public FileStorageException (String message)
	{
		super(message);
	}
	public FileStorageException(String 	message,Throwable cause)
	{
		super(message,cause);
	}
}