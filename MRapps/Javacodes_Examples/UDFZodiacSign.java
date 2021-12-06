package org.example.hadoopcodes;

import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.hadoop.hive.ql.exec.UDF;
 
public class UDFZodiacSign extends UDF{
private SimpleDateFormat df;
public UDFZodiacSign(){
df = new SimpleDateFormat("MM-dd-yyyy");
}
@SuppressWarnings("deprecation")
public String evaluate( Date bday ){
return this.evaluate( bday.getMonth(), bday.getDay() );
}
@SuppressWarnings("deprecation")
public String evaluate(String bday){
Date date = null;
try {
date = df.parse(bday);
} catch (Exception ex) {
return null;
}
return this.evaluate( date.getMonth()+1, date.getDay() );
}
public String evaluate( Integer month, Integer day ){
if (month==1) {
if (day < 20 ){
return "Capricorn";
} else {
return "Aquarius";
}
}
if (month==2){
if (day < 19 ){
return "Aquarius";
} else {
return "Pisces";
}
}
if (month==3) {
if (day < 20 ){
return "Aquarius";
} else {
return "Aries";
}
}
if (month==4) {
if (day < 20 ){
return "Aries";
} else {
return "Taurus";
}
}
if (month==5) {
if (day < 20 ){
return "Taurus";
} else {
return "Gemini";
}
}
if (month==6) {
if (day < 20 ){
return "Gemini";
} else {
return "Cancer";
}
}
if (month==7) {
if (day < 20 ){
return "Cancer";
} else {
return "Leo";
}
}
if (month==8) {
if (day < 20 ){
return "Leo";
} else {
return "Virgo";
}
}
if (month==9) {
if (day < 20 ){
return "Virgo";
} else {
return "Libra";
}
}
if (month==10) {
if (day < 20 ){
return "Libra";
} else {
return "Scorpio";
}
}
if (month==11) {
if (day < 20 ){
return "Scorpio";
} else {
return "Saggitarus";
}
}
if (month==12) {
if (day < 20 ){
return "Saggitarus";
} else {
return "Capricorn";
}
}
return null;
}
}
