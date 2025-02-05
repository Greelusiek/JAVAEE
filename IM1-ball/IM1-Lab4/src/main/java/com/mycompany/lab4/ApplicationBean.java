package com.mycompany.lab4;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Named(value = "applicationBean")
@ApplicationScoped
public class ApplicationBean {
    
    @Inject
    private PlayDao playDao;
    @Inject
    private GameDao gameDao;
    
    //@Inject
    //@Push(channel = "OmniChannel")
    //private PushContext push;
    
    @Inject
    private WebSocketSessionManager sessionManager;    
    
    private Boolean isListFresh = false;
    private final ArrayList<Play> plays = new ArrayList();
    private final ArrayList<String> users = new ArrayList();
    private Game game = null;
    private final int limit = 100;
    private final Map<String, Integer> userCard = new HashMap();
    private final Map<String, Integer> userWins = new HashMap();
    private String turn = null;
    private Boolean gameRunning = false;
    private final Random rand = new Random();
    private final ArrayList<String> symbols = new ArrayList<>(
            Arrays.asList(
                    "2", "3", "4", "5", "6", "7", "8",
                    "9", "10", "Jack", "Queen", "King", "Ace"
            )
    );

    public ApplicationBean() {      
    } 
    
    public void run() {
    }
    
    public void associate() { /*leave empty*/
    }
       
    @PostConstruct
    public void init() {
    }    

    public void playsAdd(String text) {
        if(isGameRunning()) {
            try {
                playDao.save(new Play(text, game));
                System.out.println("Play added.");
            } catch (Exception e) {
                System.err.println("Error adding play: " + e.getMessage());
            }
            isListFresh = false; 
        }
    }
    
    public ArrayList<Play> getPlays() {
        if(true) { ///
            if(true) // isListFresh == false
            {
                plays.clear();
                try {
                    playDao.findByGame(game, false, limit).forEach(plays::add); //
                    System.out.println("Play table listed successfully.");
                } catch (Exception e) {
                    System.err.println("Error listing Play table: " + e.getMessage());
                }
                isListFresh = true;
            }
            return plays;           
        }  
        return null; 
    }
    
    public ArrayList<String> getUsers() {
        return users; 
    }

    public String getTurn() {
        if (isGameRunning()) {
            return turn;
        }
        return null;
    }    

    public Boolean isGameRunning() {
        return gameRunning;
    }
    
    public void start() {
        if(!isGameRunning() && canStart()) {
            gameRunning = true;
            try {
                game = new Game();
                gameDao.save(game);
                System.out.println("Game added.");
            } catch (Exception e) {
                System.err.println("Error adding game: " + e.getMessage());
            }
            
            ///users.clear();
            plays.clear();
            isListFresh = false;
            
            userCard.clear();
            for( String u : users) {
                userCard.put(u, rand.nextInt(12));
            }
            userWins.clear();
            for( String u : users) {
                userWins.put(u, 0);
            }            
            
            playsAdd("Game Started!");
            
            turn = null;
            turn = users.get(0); //
            playsAdd(turn + " turn!");
            pushSend();
        }      
    } 
    
    public void restart() {
        if(isGameRunning()) {
            gameRunning = true;
            try {
                game = new Game();
                gameDao.save(game);
                System.out.println("Game added.");
            } catch (Exception e) {
                System.err.println("Error adding game: " + e.getMessage());
            }
            
            ///users.clear();
            plays.clear();
            isListFresh = false;
            
            userCard.clear();
            for( String u : users) {
                userCard.put(u, rand.nextInt(12));
            }
            userWins.clear();
            for( String u : users) {
                userWins.put(u, 0);
            }
            
            playsAdd("Game ReStarted!");
            
            turn = null;
            turn = users.get(0); //
            playsAdd(turn + " turn!");
            pushSend();
        }        
    }
    
    public void end() {
        if(isGameRunning()) {
            
            int winningScore = 0;
            String winner = null;
            for( int i = 0; i < users.size(); ++i ) {
                if (userWins.get(users.get(i)) >= winningScore) {
                    winningScore = userWins.get(users.get(i));
                    winner = users.get(i);
                }
            }         
            playsAdd(winner + " Won The Game!");
            playsAdd("Game Ended!");
            
            gameRunning = false;
            //game = null; // zostawiamy do odczytu
            users.clear();
            //plays.clear();
            //isListFresh = false;
            
            userCard.clear();
            userWins.clear();

            turn = null; //
            
            pushSend();
        }
    }     

    public void usersAdd(String username) {
        if (!isGameRunning()) {
            users.add(username);
        }
    }
    
    public Boolean canStart() {
        return users.size() >= 2 ;
    }    

    void nextTurn() {
        if(isGameRunning()) {
            int idx = 0;
            for( int i = 0; i < users.size(); ++i ) {
                if (users.get(i).equals(turn)) idx = i;
            }
            
            /* - - - - - - - - - - - - - - - */
            if((idx+1) == users.size()) { // koniec rundy
                
                int winningCard = 0;
                String winner = null;
                for( int i = 0; i < users.size(); ++i ) {
                    if (userCard.get(users.get(i)) >= winningCard) {
                        winningCard = userCard.get(users.get(i));
                        winner = users.get(i);
                    }
                }         
                         
                userWins.put(winner, (userWins.get(winner)+1) );
                playsAdd(winner + " Won The Round!");
                //end();
                
                /* - - - - - - - - - - - - - - - */
                for( String u : users) { // nowe losowanie // czyli nie Wojna ale analogicznie
                    userCard.put(u, rand.nextInt(12));
                }
                
            }
            
            turn = users.get((idx+1)%users.size());
            playsAdd(turn + " turn!");
        }
    }

    public Map<String, Integer> getUserCard() {
        return userCard;
    }
           
    public ArrayList<String> getSymbols() {
        return this.symbols;
    }
    
    public void pushSend() {
        //push.send("ping");
        sessionManager.send("ping");
        System.out.println("Sending ping...");
    }   
}
