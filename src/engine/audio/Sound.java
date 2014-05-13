/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package engine.audio;

import java.net.URL;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Saeed Masoumi    
 */
public class Sound {
//    //URL addresses
    private static final URL bgMusicURL =Sound.class.getResource("../../resources/sound/Begins.mp3"); //backGround Music
    private static final URL clickURL1 =Sound.class.getResource("../../resources/sound/effect/btnClicked-1.mp3"); //backGround Music
    private static final URL clickURL2 =Sound.class.getResource("../../resources/sound/effect/btnClicked-2.mp3"); //backGround Music
//    //media players
    public  static MediaPlayer bgMusic =new MediaPlayer(new Media(bgMusicURL.toString()));
    public  static AudioClip  clicked1 =new AudioClip (clickURL1.toString());
    public  static AudioClip  clicked2 =new AudioClip (clickURL2.toString());
  
}
