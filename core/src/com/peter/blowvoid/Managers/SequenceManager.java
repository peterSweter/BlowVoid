package com.peter.blowvoid.Managers;

import com.peter.blowvoid.BVoid;
import com.peter.blowvoid.entities.Sequence;

/**
 * Created by peter on 2/17/16.
 */
public class SequenceManager {

    public BVoid game;
    public Sequence[] sequences;

    public SequenceManager(BVoid game){
        this.game = game;
        sequences = new Sequence[24];


        //sequence #0
        sequences[0] = new Sequence(game);
        sequences[0].addBomb(0, 0);
        sequences[0].setSize(bombSize(), bombSize());

        //sequence #1
        sequences[1] = new Sequence(game);
        sequences[1].addBomb(0, 0);
        sequences[1].addBomb(bombSize() + 10, 0);
        sequences[1].setSize(bombSize()*2 +10, bombSize());

        //sequence #2
        sequences[2] = new Sequence(game);
        sequences[2].addBomb(0, bombSize() + 10);
        sequences[2].addBomb(bombSize() + 10, bombSize() + 10);
        sequences[2].addBomb(bombSize() + 10, 0);
        sequences[2].addBomb(0, 0);
        sequences[2].setSize(bombSize()*2+10, bombSize()*2+10);

        //sequence #3
        sequences[3] = new Sequence(game);
        sequences[3].addBomb(0, 0);
        sequences[3].addBomb(bombSize() + 10, bombSize() + 10);
        sequences[3].addBomb(2 * (bombSize() + 10), 2 * (bombSize() + 10));
        sequences[3].addBomb(3*(bombSize() + 10), 3*(bombSize() + 10));
        sequences[3].addBomb(4*(bombSize() + 10), 4*(bombSize() + 10));
        sequences[3].setSize(5*(bombSize() + 10), 5*(bombSize() + 10));

        //sequence #4
        sequences[4] = new Sequence(game);
        sequences[4].addBomb(0, 0);
        sequences[4].addBomb(bombSize() + 10, bombSize() + 10);
        sequences[4].addBomb(2*(bombSize() + 10), 2*(bombSize() + 10));
        sequences[4].addBomb(0, 2*(bombSize() + 10));
        sequences[4].addBomb(2*(bombSize() + 10), 0);
        sequences[4].setSize(3*(bombSize() + 10), 3*(bombSize() + 10));



    }

    float bombSize(){
        return BVoid.BOMB_RADIOUS*2*BVoid.BOMB_SCALE + 20;
    }
}
