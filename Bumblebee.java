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


			peek = false;
	turnLeft(getHeading() % 90);
	ahead(moveAmount);
    // Configurações para movimentação independente
    setAdjustGunForRobotTurn(true);
    setAdjustRadarForGunTurn(true);

    // Radar inicialmente gira indefinidamente para encontrar inimigos
    setTurnRadarRight(Double.POSITIVE_INFINITY);
	moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
    // Loop principal
    while (true) {
        	// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);

        // Execute ações pendentes
        execute();
			}
		
		// Quando detectar um robô inimigo
			public void onScannedRobot(ScannedRobotEvent e) {
    // Trava radar no inimigo para manter "sniper lock"
    double radarTurn = getHeading() + e.getBearing() - getRadarHeading();
    setTurnRadarRight(Utils.normalRelativeAngleDegrees(radarTurn));

    lastScanned = e.getName();

    // Calcule posição do inimigo relativamente ao campo
    double absoluteBearing = Math.toRadians((getHeading() + e.getBearing()));
    double enemyX = getX() + Math.sin(absoluteBearing) * e.getDistance();
    double enemyY = getY() + Math.cos(absoluteBearing) * e.getDistance();

    // Estimativa linear: assume que o inimigo mantém heading e velocity
    double enemyHeading = Math.toRadians(e.getHeading());
    double enemyVelocity = e.getVelocity();

    // Escolha potência do tiro baseada na distância
    double firePower = Math.min(3.0, Math.max(0.4, 500.0 / e.getDistance()));
    double bulletSpeed = 20 - 3 * firePower; // fórmula do Robocode

    // Prever o tempo que a bala levará
    double deltaX = enemyX - getX();
    double deltaY = enemyY - getY();
    double distance = Math.hypot(deltaX, deltaY);

    double time = 0;
    double predictedX = enemyX;
    double predictedY = enemyY;

    // Iterativo para previsão (resolver interseção posição-bala)
    for (int i = 0; i < 20; i++) {
        time = distance / bulletSpeed;
        predictedX = enemyX + Math.sin(enemyHeading) * enemyVelocity * time;
        predictedY = enemyY + Math.cos(enemyHeading) * enemyVelocity * time;

        // Ajusta para limites do campo
        predictedX = Math.max(Math.min(predictedX, getBattleFieldWidth() - 18), 18);
        predictedY = Math.max(Math.min(predictedY, getBattleFieldHeight() - 18), 18);

        deltaX = predictedX - getX();
        deltaY = predictedY - getY();
        distance = Math.hypot(deltaX, deltaY);
        // Atualiza tempo para próxima iteração
        bulletSpeed = 20 - 3 * firePower;
    }

    // Calcula ângulo para mirar
    double aimAngle = Math.toDegrees(Math.atan2(predictedX - getX(), predictedY - getY()));
    double gunTurn = Utils.normalRelativeAngleDegrees(aimAngle - getGunHeading());

    setTurnGunRight(gunTurn);

    // Dispara quando a mira estiver razoavelmente alinhada
    if (Math.abs(getGunTurnRemaining()) < 10) {
        if (getEnergy() > firePower + 0.2) {
            setFire(firePower);
        }
    }

    // Mantém movimentação independente — se o inimigo se aproxima muito, tente esquivar
    if (e.getDistance() < 150) {
        // faça uma evasão curta
        direction *= -1;
        setAhead(150 * direction);
    }
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







