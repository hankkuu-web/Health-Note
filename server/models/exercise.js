/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('exercise', {
    exercise_id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true
    },
    exerciseName: {
      type: DataTypes.STRING(20),
      allowNull: false
    }
  }, {
    tableName: 'Exercise'
  });
};
