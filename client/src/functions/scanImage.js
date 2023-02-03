const scanImage = body => {
  if (!body) return [];

  return body.match(/<img [^>]*src="[^"]*"[^>]*>/gm).map(x => x.replace(/.*src="([^"]*)".*/, '$1'));
};

export default scanImage;
