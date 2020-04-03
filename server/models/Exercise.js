/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('Exercise', {
    exerciseCode: {
      type: DataTypes.INTEGER(11),
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
