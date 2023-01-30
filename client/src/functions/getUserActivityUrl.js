const getUserActivityUrl = (id, activity, page) => {
  if (activity === 'series') {
    return `members/${id}/series?page=${page}&size=5`;
  }
  if (activity === 'post') {
    return `members/${id}/post?page=${page}&size=5&sort=newest`;
  }
  return `members/${id}/showcases?offset=${page}&size=5`;
};

export default getUserActivityUrl;