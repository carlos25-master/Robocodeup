package Transformers;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Bumblebee - Um robô que se movimenta em círculos e atira com precisão.
 */
	public class Bumblebee extends Robot
	{
	/**
	 * run: Bumblebee's default behavior
		 */
		public void run() {
			
	      // Cores do robô
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);
		setBulletColor(Color.black);
		setScanColor(Color.black);
		    }


		//Obtém as dimensões do campo de batalha.
			getBattleFieldWidth() 
			getBattleFieldHeight()
			}
			
	    // Mantém o radar se movendo independentemente do canhão
			setAdjustRadarForGunTurn(true);
			setAdjustGunForRobotTurn(true);
	        }
		
		// Gira o radar indefinidamente
			while (true) {
			setTurnRadarRight(360);
			execute();
			}
				
		// Movimentos principais do Robô
			ahead(100);
			turnGunRight(360);
			back(100);
			turnLeft(360);
			}
		
		// Quando detectar um robô inimigo
			public void onScannedRobot(ScannedRobotEvent e) {
			}
				
	    // Calcular a distância ao inimigo
			double distance = e.getDistance();
			}

		// Girar o canhão para mirar no inimigo
		   double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
		   double gunTurn = robocode.util.Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians());
		   setTurnGunRightRadians(gunTurn);
			}

		//Giram o canhão, independente do veículo
			turnGunRight(double degree) / turnGunLeft(double degree)
			}
		
		// Disparar com potência proporcional à proximidade
		  if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10) {
		  double firePower = Math.min(400 / distance, 3);
		  setFire(firePower);
		  	}

		// Método executado quando o robô é atingido por um disparo
			onHitByBullet(HitByBulletEvent e){
		  	}

		// Movimento lateral para dificultar ser atingido
			setTurnRight(e.getBearing() + 90 - 15);{
			setAhead(100);
		 	}
		
		// Quando acertar outro robô com um tiro
			public void onBulletHit(BulletHitEvent e) {
			setAhead(50);
			}
			
		// Quando for atingido por um tiro
			public void onHitByBullet(HitByBulletEvent e) {
			back(50);
			turnRight(30);
			}

public void onHitRobot(HitRobotEvent e) {
    // Se colidir com inimigo, dê ré e vire para não ficar preso; não atira por colisão
    if (e.isMyFault()) {
        setBack(50);
        direction *= -1;
    }}
		
		// Quando bater na parede
public void onHitWall(HitWallEvent e) {
    // Ao bater no muro inverte a direção e recua um pouco
    direction *= -1;
    setBack(wallMargin);
	setTurnRadarRight(360);
			}
		
			}
		}
	}
}	







