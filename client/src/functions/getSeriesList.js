const getSeriesList = (category, page) => {
  if (category !== 'All') {
    return `categories/${category}/series?page=${page}&size=10&sort=newest`;
  }

  return `series?page=${page}&size=10&sort=newest`;
};

export default getSeriesList;
