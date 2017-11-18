# RTFgenerator
A simple java class to generate new .rtf files

Simple example of use
<code>
RTFgenerator a = new RTFgenerator("filename.rtf");
a.addText("test");
a.addText(" test\n");
a.addText("test\n");
a.openTable("7000", "9000");
a.addRowTable("o", "a");
a.addRowTable("o", "a");
a.addRowTable("o", "a");
a.createFile();
</code>

<i>Special chars</i>
<ul>
 <li>/b /b0 to bold</li>
 <li>/i /i0 to italic</li>
 <li>/u /u0 to underlined</li>
</ul>
