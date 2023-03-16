package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import supportGUI.Circle;

public class Naif {
	public Naif ( )
	{
		
	}
	public Circle calculCercleMin(ArrayList<Point> inputPoints) {
		ArrayList<Point> points = (ArrayList<Point>) inputPoints.clone();
		if (points.size() < 1)
			return null;
		double x, y, cRadius;
		for (Point p : points) {
			for (Point q : points) {
				x = .5 * (p.x + q.x);
				y = .5 * (p.y + q.y);
				cRadius = 0.25 * ((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
				boolean allHit = true;
				for (Point s : points)
					if ((s.x - x) * (s.x - x) + (s.y - y) * (s.y - y) > cRadius) {
						allHit = false;
						break;
					}
				if (allHit)
					return new Circle(new Point((int) x, (int) y), (int) Math.sqrt(cRadius));
			}
		}
		
		double resX = 0;
		double resY = 0;
		double resRadius = Double.MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					Point p = points.get(i);
					Point q = points.get(j);
					Point r = points.get(k);
					
					// si les trois sont colineaires on passe
					if ((q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x) == 0)
						continue;
					// si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les
					// echange
					if ((p.y == q.y) || (p.y == r.y)) {
						if (p.y == q.y) {
							p = points.get(k); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							r = points.get(i); // parce que les trois points sont non-colineaires
						} else {
							p = points.get(j); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							q = points.get(i); // parce que les trois points sont non-colineaires
						}
					}
					
					double a = p.getX() - q.getX();
					double b = p.getY() - q.getY();
					double c = p.getX() - r.getX();
					double d = p.getY() - r.getY();
					double e = ((p.getX() * p.getX() - q.getX() * q.getX())
							- (q.getY() * q.getY() - p.getY() * p.getY())) / 2;
					double f = ((p.getX() * p.getX() - r.getX() * r.getX())
							- (r.getY() * r.getY() - p.getY() * p.getY())) / 2;

					x = (e * d - b * f) / (a * d - b * c);
					y = (a * f - e * c) / (a * d - b * c);
					Point center = new Point((int) x, (int) y);
					cRadius = center.distance(p);
					if (cRadius >= resRadius)
						continue;
					boolean allHit = true;
					for (Point s : points)
						if ((s.x - x) * (s.x - x) + (s.y - y) * (s.y - y) > cRadius * cRadius) {
							allHit = false;
							break;
						}
					if (allHit) {
						resX = x;
						resY = y;
						resRadius = cRadius;
					}
				}
			}
		}
		
		Circle c = new Circle(new Point((int) resX, (int) resY), (int)resRadius);
		return c;
	}
	
	
}
