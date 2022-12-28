package interfaces;

import java.io.File;

import util.TypeShuffle;

public interface ClickListener {
	void onClick(File image, int size, TypeShuffle typeShuffle);
}
