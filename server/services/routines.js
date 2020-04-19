const { db } = require('../models');

const createOrUpdate = async (body) => {
  const { routines } = body;

  // Routine 과 WeightTraining Table이 분리되어 있기 때문에 비즈니스에서 분기 처리한다
  const cardioArray = [];
  const weightArray = [];
  for (let item of routines) {
    if (item.isCadio == true) cardioArray.push(item);
    else weightArray.push(item);
  }

  await db.routine
    .bulkCreate(cardioArray, {
      updateOnDuplicate: ['routineOrder', 'cardioTime'],
    })
    .catch((err) => {
      throw new Error(err);
    });

  await db.routine
    .bulkCreate(weightArray, {
      updateOnDuplicate: ['setCount', 'repetitions', 'maxWeight'],
    })
    .catch((err) => {
      throw new Error(err);
    });
};

const getByScheduleId = async (params) => {
  const { schedule_id } = params;

  const result = await db.routine
    .findAll({
      where: { schedule_id: schedule_id },
      //include: { model: db.exercise },
      raw: true,
      nest: true,
    })
    .catch((err) => {
      throw new Error(err);
    });

  return result;
};

const remove = async (query) => {
  const { schedule_id, id } = query;

  await db.routine
    .destroy({
      where: { schedule_id: schedule_id, id: id },
    })
    .catch((err) => {
      throw new Error(err);
    });
};

module.exports = { createOrUpdate, getByScheduleId, remove };
