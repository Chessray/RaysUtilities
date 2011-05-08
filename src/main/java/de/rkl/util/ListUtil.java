package de.rkl.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for {@link List Lists}.
 * 
 * @author ray
 */
public final class ListUtil
{
	private ListUtil()
	{}

	/**
	 * Splits {@code original} into {@link List#subList(int, int) sublists}
	 * backed by {@code original} with a maximum size of {@code splitSize}.
	 * <em>Note</em> If {@code original}'s size is an integer multiple of
	 * {@code splitSize}, the last {@link List} in the returned {@link List}
	 * will be empty.
	 * 
	 * @param <E>
	 *            The Element type
	 * @param original
	 *            The {@link List} to split.
	 * @param splitSize
	 *            The sublists' maximum size.
	 * @return A {@link List} of the sublists, sorted in ascending order
	 *         according to {@code original}.
	 */
	public static <E> List<List<E>> split(final List<E> original,
			final int splitSize) {
		if (splitSize <= 0)
		{
			throw new IllegalArgumentException(
					"splitSize must be a positive integer.");
		}

		final List<List<E>> splitList = new ArrayList<List<E>>();

		if (original != null)
		{
			synchronized (original)
			{
				final int originalSize = original.size();
				final int numberOfSplits = originalSize / splitSize;
				for (int i = 0; i <= numberOfSplits; i++)
				{
					splitList.add(original.subList(splitSize * i,
							Math.min(splitSize * i + 1, originalSize)));
				}
			}
		}

		return splitList;
	}
}
