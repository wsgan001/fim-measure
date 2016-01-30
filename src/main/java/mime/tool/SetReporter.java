package mime.tool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;

public interface SetReporter {

	public void report(Itemset set);

	public void close();

	public static class NullReporter implements SetReporter {
		@Override
		public void report(Itemset set) {
		}

		@Override
		public void close() {
		}
	}

	public static class CMDReporter implements SetReporter {
		private static final String nl = "\n";
		private static final int BufferSize = 1;

		private final StringBuilder builder;
		private int counter;

		public CMDReporter() {
			this.builder = new StringBuilder();
			this.counter = 0;
		}

		@Override
		public void report(Itemset set) {
			for (Item item : set) {
				builder.append(item.getId() + " ");
			}
			builder.setLength(builder.length() - 1);
			builder.append(nl);

			counter++;
			if (counter % BufferSize == 0) {
				System.out.print(builder.toString());
				builder.setLength(0);
			}
		}

		@Override
		public void close() {
			if (builder.length() != 0) {
				System.out.print(builder.toString());
				builder.setLength(0);
			}
		}
	}

	public static class FileReporter implements SetReporter {
		private BufferedWriter writer;

		public FileReporter(String fileName) {
			try {
				this.writer = new BufferedWriter(new FileWriter(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void report(Itemset set) {
			try {
				for (Item item : set) {
					writer.append(item.getId() + " ");
				}
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void close() {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}