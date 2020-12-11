import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinesAdapter extends MouseAdapter {
    // Constants
    private final int CELL_SIZE  = 10;

    Board board;

    public MinesAdapter(Board board) {
        this.board = board;
	}

	public void mousePressed(MouseEvent e) {
        int pressedCol = e.getX() / CELL_SIZE;
        int pressedRow = e.getY() / CELL_SIZE;
        boolean doRepaint = false;
        Cell pressedCell;
        if (!board.inGame) {
            board.newGame();
            board.repaint();
        }

        if ((pressedCol < 0 || pressedCol >= board.columns)
            || (pressedRow < 0 || pressedRow >= board.rows)) {
            return;
        }

        pressedCell = board.cells[pressedRow][pressedCol];
        if (e.getButton() == MouseEvent.BUTTON3) {
            doRepaint = true;
            if (!pressedCell.isCovered()) {
                return;
            }

            if (!pressedCell.isMarked()) {
                pressedCell.setMark(true);
                board.remainderMines--;
            } else {
                pressedCell.setMark(false);
                board.remainderMines++;
            }

            board.statusBar.setText(Integer.toString(board.remainderMines));
        } else {
            if (pressedCell.isMarked() || !pressedCell.isCovered()) {
                return;
            }

            doRepaint = true;
            pressedCell.uncover();
            if (pressedCell.isMine()) {
                board.inGame = false;
            } else if (pressedCell.isEmpty()) {
                board.findEmptyCells(pressedRow, pressedCol, 0);
            }
        }

        if (doRepaint) {
            board.repaint();
        }
    }
}
