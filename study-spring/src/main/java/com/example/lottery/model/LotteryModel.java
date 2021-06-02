package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.lottery.aop.Audit;

@SuppressWarnings("serial")
@Component
@SessionScope
@Audit
public class LotteryModel implements Serializable {
	private List<List<Integer>> numbers;

	public LotteryModel() {
		numbers = new ArrayList<>();
	}

	public List<List<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotteryModel [numbers=" + numbers + "]";
	}

}
