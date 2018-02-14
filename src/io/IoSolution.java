package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import model.Drone;
import model.Instruction;
import model.Solution;

public class IoSolution {

	public static void save(File repertory, Solution sol) {
		try {
			File savedOutputFile = new File(repertory, getSolutionName(sol));
			BufferedWriter bw = new BufferedWriter(new FileWriter(savedOutputFile));

			bw.write("" + sol.instructions.size());
			bw.newLine();
			
			Collections.sort(sol.instructions, new Comparator<Instruction>() {
				public int compare(Instruction i1, Instruction i2) {
					return i1.turnNb - i2.turnNb;
				}
			});
			
			for (Instruction instr : sol.instructions) {
				bw.write(instr.command);
				bw.newLine();
			}

			bw.close();
			System.out.println("... Saved : " + savedOutputFile.toString());
		} catch (IOException e) {
			System.out.println("... ERROR while saving file");
			e.printStackTrace();
		}
	}

	private static String getSolutionName(Solution sol) {
		return sol.problem.name + "_" + sol.getScore() + ".txt";
	}

}