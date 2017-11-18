package chkrr00k.persister;
//"proudly" made by chkrr00k
// LGPL licence

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RTFgenerator {

	/*
	 *  /b /b0 to bold
	 *  /i /i0 to italic
	 *  /u /u0 to underlined
	 */
	
	private File file;
	private StringBuilder strbld;
	
	public RTFgenerator(File file) throws FileNotFoundException {
		this.file = file;
		this.strbld = new StringBuilder("{\\rtf1\\ansi\n");
	}
	public RTFgenerator(String file) throws FileNotFoundException {
		this(new File(file));
	}

	public void addText(String input) {
		this.addText(input, "");
	}
	
	public void createFile() throws IOException{
		this.strbld.append("}");
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file)));
		writer.write(this.strbld.toString());
		writer.flush();
		writer.close();
	}
	
	public void openTable(String... size){
		this.strbld.append("{\\pard\n\\trowd\n");
		Arrays.asList(size).forEach(x -> this.strbld.append("\\cellx" + x));
	}
	
	public void addRowTable(String... input){
		Arrays.asList(input).forEach(x -> this.strbld.append("\\intbl " + x + " \\cell\n"));
		this.strbld.append("\\row\n");
	}

	public void closeTable(){
		this.strbld.append("\n}\n");
	}
	
	private void addText(String input, String modifier){
		if(input == null){
			return;
		}
		StringTokenizer tok = new StringTokenizer(input, "\n");
		while(tok.countTokens() > 0){
			this.strbld.append("\\pard"+ modifier +" " + tok.nextToken().trim() + "\\par\n");
		}
	}
	
	
	public void addLeftText(String input){
		this.addText(input, "\\ql");
	}
	
	public void addRightText(String input){
		this.addText(input, "\\qr");
	}
	
	public void addCenteredText(String input){
		this.addText(input, "\\qc");
	}
	
	public void addJustifiedText(String input){
		this.addText(input, "\\qj");
	}
	
	public void addNewLine(){
		this.strbld.append("\n\\pard\\par\n");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException{
		RTFgenerator a = new RTFgenerator("filename.rtf");
		a.addText("test");
		a.addText(" test\n");
		a.addText("test\n");
		a.openTable("7000", "9000");
		a.addRowTable("o", "a");
		a.addRowTable("o", "a");
		a.addRowTable("o", "a");
		a.addRowTable("o", "a");
		a.createFile();
	}
	
}
