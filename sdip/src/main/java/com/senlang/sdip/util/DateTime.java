/**
 * 
 */
package com.senlang.sdip.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mc.D
 *
 */
public final class DateTime {
	Calendar value;

	private DateTime(Date date) {
		this.value = Calendar.getInstance();
		this.value.setTime(date);
	}

	private DateTime(Calendar calendar) {
		this.value = calendar;
	}

	public static DateTime of(Date date) {
		return new DateTime(date);
	}

	public static DateTime of(Calendar calendar) {
		return new DateTime(calendar);
	}

	public static DateTime of(int year, int month, int day) {
		return DateTime.of(year, month, day, 0, 0, 0);
	}

	public static DateTime of(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month, day, hour, minute, second);
		return DateTime.of(c);
	}

	public static DateTime now() {
		return DateTime.of(new Date());
	}

	public int getYear() {
		return this.value.get(Calendar.YEAR);
	}

	public int getMonth() {
		return this.value.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		return this.value.get(Calendar.DAY_OF_MONTH);
	}

	public int getHour() {
		return this.value.get(Calendar.HOUR_OF_DAY);
	}

	public int getHour12() {
		return this.value.get(Calendar.HOUR);
	}

	public int getMinute() {
		return this.value.get(Calendar.MINUTE);
	}

	public int getSecond() {
		return this.value.get(Calendar.SECOND);
	}

	public int getMilliSecond() {
		return this.value.get(Calendar.MILLISECOND);
	}

	public DateTime setYear(int year) {
		this.value.set(Calendar.YEAR, year);
		return this;
	}

	public DateTime setMonth(int month) {
		this.value.set(Calendar.MONTH, month);
		return this;
	}

	public DateTime setDay(int day) {
		this.value.set(Calendar.DAY_OF_MONTH, day);
		return this;
	}

	public DateTime setHour(int hour) {
		this.value.set(Calendar.HOUR_OF_DAY, hour);
		return this;
	}

	public DateTime setMinute(int minute) {
		this.value.set(Calendar.MINUTE, minute);
		return this;
	}

	public DateTime setSecond(int second) {
		this.value.set(Calendar.SECOND, second);
		return this;
	}

	public DateTime setMilliSecond(int milliSecond) {
		this.value.set(Calendar.MILLISECOND, milliSecond);
		return this;
	}

	public String asY4M2D2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.value.getTime());
	}

	public String asY4M2D2H2M2S2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.value.getTime());
	}

	public Date toDate() {
		return this.value.getTime();
	}

	// public Calendar get(){
	// return this.value;
	// }
}
