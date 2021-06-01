package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("lottery")
@SessionScoped
// Passivation scoped defined bean must be passivation capable
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
