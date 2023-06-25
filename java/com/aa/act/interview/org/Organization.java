package com.aa.act.interview.org;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

public abstract class Organization {

	private Position root;

	public Organization() {
		root = createOrganization();
	}

	protected abstract Position createOrganization();

	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		// A standard null check on the title passed in. Not STRICTLY necessary, but I
		// always like a check on important strings like this.
		if (title != null && !title.strip().isEmpty()) {
			Employee worker = new Employee(UUID.randomUUID(), person);
			if (title.equals(root.getTitle())) {
				root.setEmployee(Optional.of(worker));
				return Optional.of(root);
			} else {
				// The null check is not STRICTLY null for this exercise, but it feels wrong not
				// to include it.
				Position foundJob = this.findPositionForHire(root.getDirectReports(), title);
				if (foundJob != null) {
					foundJob.setEmployee(Optional.of(worker));
					return Optional.of(foundJob);
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	// A recursive method to find the correct position for a potential hire.
	private Position findPositionForHire(Collection<Position> pos, String title) {
		Iterator<Position> it = pos.iterator();
		while (it.hasNext()) {
			Position job = it.next();
			if (job.getTitle().equals(title)) {
				return job;
			} else {
				Position jobFound = findPositionForHire(job.getDirectReports(), title);
				if (jobFound != null) {
					return jobFound;
				}
			}
		}
		return null;
	}

	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for (Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "  "));
		}
		return sb.toString();
	}
}
