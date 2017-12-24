package com;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{

	public static void main(String[] args) throws InterruptedException {
		// TODO 自动生成的方法存根
		Scanner sc = new Scanner(System.in);
		int size = 20;
		int cellNum = 20;
		System.out.println("请输入细胞图大小");
		size = sc.nextInt();
		Game g = new Game(size);
	}
	
	int size;
	int cellNum;
	int cells[][];
	int currentCells[][];
	Container c = getContentPane();
	JComponent[][] jps;
	
	Game(int size) throws InterruptedException{
		super();
		// 多造外围的一圈就可以不用考虑边界了，也就是size+2
		this.size = size + 2;
		this.cellNum = size/2;
		cells = new int[this.size][this.size];
		currentCells = new int[this.size][this.size];
		initCells();
		// 界面
		c.setLayout(new GridLayout(size, size));
		jps = new JComponent[this.size][this.size];
		for (int i = 1; i < this.size - 1; i++) {
			for (int j = 1; j < this.size - 1; j++) {
				JPanel jp = new JPanel();
				jps[i][j] = jp;
				jp.setSize(20, 20);
				jp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				jp.setBackground(Color.white);
				getContentPane();
				c.add(jp);
			}
		}

		this.setSize(size * 20, size * 20);
		this.setTitle("生命的游戏");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		while (true) {
			showCells();
			liveOrDead();
			Thread.sleep(500);
		}
	}
	
	// 输出到界面上
		public void showCells() {
			for (int i = 1; i < size - 1; i++) {
				for (int j = 1; j < size - 1; j++) {
					if (cells[i][j] == 0) {
						jps[i][j].setBackground(Color.GRAY);
					} else {
						jps[i][j].setBackground(Color.GREEN);
					}
				}
			}
		}

		// 打印二维数组的值
		public void printCells() {
			for (int i = 1; i < size - 1; i++) {
				for (int j = 1; j < size - 1; j++) {
					System.out.print(cells[i][j] + " ");
				}
				System.out.println("\n");
			}
			System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
		}

		// 根据输入的存活细胞数随机重置二维数组 用Random方法；
		public void initCells() {
			int i = 1;
			while (this.cellNum > 0) {
				for (int j = 1; j < size - 1; j++) {
					if (cells[i][j] == 0 && Math.random() > 0.95) {
						cells[i][j] = 1;
						cellNum -= 1;
					}
				}
				if (i == size - 2)
					i = 1;
				else
					i += 1;
			}
			printCells();
		}

		// 细胞按法则变换一次方法 存入currentCells
		public void liveOrDead() {
			for (int i = 1; i < size - 1; i++) {
				for (int j = 1; j < size - 1; j++) {
					int countAlive = 0;
					countAlive += cells[i - 1][j - 1] + cells[i - 1][j] + cells[i - 1][j + 1];
					countAlive += cells[i][j - 1] + cells[i][j + 1];
					countAlive += cells[i + 1][j - 1] + cells[i + 1][j] + cells[i + 1][j + 1];
					if (cells[i][j] == 1 && (countAlive < 2 || countAlive > 3)) {
						currentCells[i][j] = 0;
					}
					if (cells[i][j] == 0 && countAlive == 3)
						currentCells[i][j] = 1;
					if (countAlive == 2 || countAlive == 3) {
						currentCells[i][j] = 1;
					}
				}
			}
			// 更新细胞存活图
			currentToCells();
			// 打印到控制台
			printCells();
		}

		// 把当前的细胞存活状态图复制给cells
		public void currentToCells() {
			for (int i = 1; i < size - 1; i++) {
				for (int j = 1; j < size - 1; j++) {
					cells[i][j] = currentCells[i][j];
				}
			}
		}

}
