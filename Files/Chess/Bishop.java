import java.awt.Color;

public class Bishop
{
	public static boolean isValidMoveBlack(int fx, int fy, int sx, int sy)
	{
		if(sx <= 1 || sy >= 9)
			return false;
		for (int i = 0; i < 6; i++)
		{ // for loop to iterate through all
			// ///possible white pieces and makes sure
			// that the second position is not a
			// white piece.

			if (GUIPanel.b1[sy][sx].getText().equals(Character.toString(GUIPanel.AllBlackPieces[i])))
				return false;
		}
		if (GUIPanel.b1[fy][fx].getText() != "\u265D")
		{

			return false;
		}
		if (Math.abs(sy - fy) != Math.abs(sx - fx))
			return false;
		return true;
	}

	public static boolean isValidMoveWhite(int fx, int fy, int sx, int sy)
	{
		if(sx <= 1 || sy >= 9)
			return false;
		for (int i = 0; i < 6; i++)
		{ // for loop to iterate through all
			// ///possible white pieces and makes sure
			// that the second position is not a
			// white piece.

			if (GUIPanel.b1[sy][sx].getText().equals(Character.toString(GUIPanel.AllWhitePieces[i])))
				return false;
		}
		if (GUIPanel.b1[fy][fx].getText() != "\u2657")
			return false;
		if (Math.abs(sy - fy) != Math.abs(sx - fx))
			return false;
		if (sx > fx && sy > fy)
		{
			
			for (int x = fx+1; x < sx; x++)
			{
					if(GUIPanel.b1[x][fy+x-fx].getText() != "")
					{
						System.out.println(GUIPanel.b1[x][fy+x-fx].getText());
						return false;
					}
			}
			return true;
		}
		return true;
	}

	public static void bmove(int fx, int fy, int sx, int sy)
	{

		GUIPanel.b1[sy][sx].setText("\u265D");
		GUIPanel.b1[sy][sx].setForeground(Color.BLACK);
		GUIPanel.b1[fy][fx].setText("");

	}

	public static void wmove(int fx, int fy, int sx, int sy)
	{
		GUIPanel.b1[sy][sx].setText("\u2657");
		GUIPanel.b1[sy][sx].setForeground(Color.WHITE);
		GUIPanel.b1[fy][fx].setText("");

	}

}