import { useRef, useCallback, useEffect } from 'react';

const useIntersect = (onIntersect, options) => {
  const ref = useRef(null);

  // intersectionObserver 인자로 사용할 콜백작성
  const callback = useCallback(
    (entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) onIntersect(entry, observer);
      });
    },
    [onIntersect],
  );

  useEffect(() => {
    // ref.current 가 지정되지 않은 경우 예외처리
    if (!ref.current) return {};

    // 위에서 작성한 함수를 intersectionObserver 의 콜백으로 등록
    const observer = new IntersectionObserver(callback, options);
    observer.observe(ref.current);

    return () => observer.disconnect();
  }, [ref, options, callback]);

  return ref;
};

export default useIntersect;
