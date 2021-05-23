package com.online.flowers.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.FlowersRepo;

@Service
public class FlowersService {
	
	@Autowired
	private FlowersRepo _repo;
	
	public FlowersModel saveImage(MultipartFile file, String category, String price, String description) {
		FlowersModel flower = null;
		try {
			flower = new FlowersModel(file.getName(), compressBytes(file.getBytes()), category, price, description);
			return _repo.save(flower);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<FlowersModel> getImage(Integer id) {
		return _repo.findById(id);
	}
	
	public List<FlowersModel> getImages() throws IOException{
		
		 return _repo.findAll().stream()
			 		.map(m -> new FlowersModel(m.getName(), 
			 				decompressBytes(m.getPhoto()),m.getCategory(), 
			 				m.getPrice(), m.getDescription()))
			 		.collect(Collectors.toList());
	 

	}
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[2048];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
